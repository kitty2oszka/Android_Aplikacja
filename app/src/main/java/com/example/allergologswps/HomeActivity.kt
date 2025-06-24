package com.example.allergologswps

import android.os.Bundle
import android.content.Intent
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.allergologswps.api.OpenFoodFactsApi
import com.example.allergologswps.api.ProductDetail
import com.example.allergologswps.api.ProductResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Handler
import android.os.Looper
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> true
                R.id.nav_journal -> {
                    startActivity(Intent(this, JournalActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Dodaj produkt
        findViewById<LinearLayout>(R.id.buttom_add_product).setOnClickListener {
            Toast.makeText(this, "Dodawanie produktu – do zaimplementowania", Toast.LENGTH_SHORT).show()
        }

        // Ostatnio dodane produkty
        val recentRecyclerView = findViewById<RecyclerView>(R.id.recentProductsRecyclerView)
        recentRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val recentProducts = mutableListOf<ProductDetail>()
        val recentAdapter = ProductListAdapter(recentProducts)
        recentRecyclerView.adapter = recentAdapter

        val barcodes = listOf(
            "3274080005003", // Garnier Fructis
            "3574669900042", // Nivea Creme
            "3059943009756", // L'Oréal Paris
            "8712561205279", // Dove
            "4005808809842"  // Nivea Men
        )
        val retrofit = Retrofit.Builder()
            .baseUrl("https://world.openfoodfacts.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(OpenFoodFactsApi::class.java)
        val authHeader = "Basic " + android.util.Base64.encodeToString("off:off".toByteArray(), android.util.Base64.NO_WRAP)

        barcodes.forEach { barcode ->
            api.getProduct(barcode, authHeader).enqueue(object : Callback<ProductResponse> {
                override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                    val product = response.body()?.product
                    if (product != null) {
                        recentProducts.add(product)
                        recentAdapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    // Obsługa błędu (np. log)
                }
            })
        }

        // CZĘSTO DODAWANE
        val frequentRecyclerView = findViewById<RecyclerView>(R.id.frequentProductsRecyclerView)
        frequentRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val products = mutableListOf<ProductApi>()
        val adapter = ProductAdapter(products)
        frequentRecyclerView.adapter = adapter

        val handler = Handler(Looper.getMainLooper())
        val executor = Executors.newSingleThreadExecutor()
        val productIds = listOf(
            "737628064502", // Heinz Tomato Ketchup
            "3017620422003", // Nutella
            "5000159484695", // Coca-Cola
            "7622210449283"  // Milka
        )
        for (id in productIds) {
            executor.execute {
                try {
                    val url = URL("https://world.openfoodfacts.org/api/v0/product/$id.json")
                    val conn = url.openConnection() as HttpURLConnection
                    conn.requestMethod = "GET"
                    conn.connectTimeout = 5000
                    conn.readTimeout = 5000
                    val responseCode = conn.responseCode
                    if (responseCode == 200) {
                        val stream = conn.inputStream.bufferedReader().use { it.readText() }
                        val json = JSONObject(stream)
                        val productJson = json.optJSONObject("product")
                        val name = productJson?.optString("product_name") ?: "Brak nazwy"
                        val imageUrl = productJson?.optString("image_front_small_url")
                        val product = ProductApi(id, name, imageUrl)
                        handler.post {
                            products.add(product)
                            adapter.notifyItemInserted(products.size - 1)
                        }
                    }
                    conn.disconnect()
                } catch (_: Exception) {
                    // Możesz dodać logowanie błędów
                }
            }
        }
    }
}
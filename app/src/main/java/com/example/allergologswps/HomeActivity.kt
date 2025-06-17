package com.example.allergologswps

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.allergologswps.api.OpenFoodFactsApi
import com.example.allergologswps.api.ProductDetail
import com.example.allergologswps.api.ProductResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        bottomNav.selectedItemId = R.id.nav_home // zaznaczony domyślnie

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> true // nic nie rób, już jesteśmy
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

        val loadingIcon = findViewById<ImageView>(R.id.frequentProductsRecyclerView)
        loadingIcon.setOnClickListener {
            startActivity(Intent(this, ProductListActivity::class.java))
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
    }
}
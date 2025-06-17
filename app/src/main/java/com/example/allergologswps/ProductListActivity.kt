package com.example.allergologswps

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.allergologswps.api.OpenFoodFactsApi
import com.example.allergologswps.api.ProductDetail
import com.example.allergologswps.api.ProductResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = RecyclerView(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        setContentView(recyclerView)

        val backArrow = findViewById<ImageView>(R.id.productListBack)
        backArrow.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // Usuwamy obsługę Room i pobieramy produkty z API
        val barcodes = listOf(
            "3274080005003", // Garnier Fructis
            "3574669900042", // Nivea Creme
            "3059943009756", // L'Oréal Paris
            "8712561205279", // Dove
            "4005808809842"  // Nivea Men
        )
        val products = mutableListOf<ProductDetail>()
        val adapter = ProductListAdapter(products)
        recyclerView.adapter = adapter

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
                        products.add(product)
                        adapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    // Obsługa błędu (np. log)
                }
            })
        }
    }
}

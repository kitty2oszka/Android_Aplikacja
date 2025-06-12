package com.example.allergologswps

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        CoroutineScope(Dispatchers.IO).launch {
            val products = AppDatabase.getDatabase(this@ProductListActivity).journalDao().getAll()
            withContext(Dispatchers.Main) {
                recyclerView.adapter = ProductListAdapter(products)
            }
        }
    }
}

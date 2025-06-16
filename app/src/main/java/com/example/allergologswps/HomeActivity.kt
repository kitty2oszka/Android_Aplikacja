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
    }
}
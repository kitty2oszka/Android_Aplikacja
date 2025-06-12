package com.example.allergologswps

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.allergologswps.databinding.ActivityAddProductBinding
import android.widget.ImageView
import android.content.Intent
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val nameEdit = findViewById<EditText>(R.id.productNameEditText)
        val noteEdit = findViewById<EditText>(R.id.productNoteEditText)
        val saveButton = findViewById<Button>(R.id.saveProductButton)

        val db = AppDatabase.getDatabase(this)
        val dao = db.productDao()

        saveButton.setOnClickListener {
            val name = nameEdit.text.toString()
            val note = noteEdit.text.toString()

            if (name.isNotEmpty()) {
                val product = Product(name = name, note = note)
                lifecycleScope.launch {
                    dao.insert(product)
                    finish() // wróć do Home
                }
            } else {
                Toast.makeText(this, "Wpisz nazwę", Toast.LENGTH_SHORT).show()
            }
        }

        val returnButton = findViewById<ImageView>(R.id.add_product_return)
        returnButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}
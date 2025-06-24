package com.example.allergologswps

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        title = "Ustawienia"

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_profile
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_journal -> {
                    startActivity(Intent(this, JournalActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_profile -> false // już jesteśmy
                else -> false
            }
        }

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val changeEmailButton = findViewById<Button>(R.id.changeEmailButton)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val changePasswordButton = findViewById<Button>(R.id.changePasswordButton)
        val newsletterSwitch = findViewById<Switch>(R.id.newsletterSwitch)

        changeEmailButton.setOnClickListener {
            val newEmail = emailEditText.text.toString().trim()
            if (newEmail.isEmpty()) {
                Toast.makeText(this, "Podaj nowy e-mail", Toast.LENGTH_SHORT).show()
            } else {
                val prefs = getSharedPreferences("auth", MODE_PRIVATE)
                prefs.edit().putString("email", newEmail).apply()
                Toast.makeText(this, "E-mail został zmieniony", Toast.LENGTH_SHORT).show()
            }
        }

        changePasswordButton.setOnClickListener {
            val newPassword = passwordEditText.text.toString()
            if (newPassword.length < 6) {
                Toast.makeText(this, "Hasło musi mieć co najmniej 6 znaków", Toast.LENGTH_SHORT).show()
            } else {
                val prefs = getSharedPreferences("auth", MODE_PRIVATE)
                prefs.edit().putString("password", newPassword).apply()
                Toast.makeText(this, "Hasło zostało zmienione", Toast.LENGTH_SHORT).show()
            }
        }
        // newsletterSwitch nie wykonuje żadnej akcji
    }
}

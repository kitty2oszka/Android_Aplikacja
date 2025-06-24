package com.example.allergologswps

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val backButton = findViewById<android.widget.ImageView>(R.id.backButton)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            // Prosta walidacja:
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showToast("Podaj poprawny e-mail")
                return@setOnClickListener
            }

            if (password.length < 6) {
                showToast("Hasło musi mieć co najmniej 6 znaków")
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                showToast("Hasła nie są takie same")
                return@setOnClickListener
            }

            // Jeśli wszystko OK:
            // Zapisz dane do SharedPreferences
            val prefs = getSharedPreferences("auth", MODE_PRIVATE)
            prefs.edit().putString("email", email).putString("password", password).apply()
            showToast("Rejestracja zakończona sukcesem!")
            // Przejście do ekranu głównego po rejestracji
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
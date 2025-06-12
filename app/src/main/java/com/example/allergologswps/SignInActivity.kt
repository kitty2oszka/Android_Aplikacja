package com.example.allergologswps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val emailField = findViewById<EditText>(R.id.emailEditText)
        val passwordField = findViewById<EditText>(R.id.passwordEditText)
        val signInButton = findViewById<Button>(R.id.registerButton)

        signInButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            val prefs = getSharedPreferences("auth", MODE_PRIVATE)
            val savedEmail = prefs.getString("email", "")
            val savedPassword = prefs.getString("password", "")

            if (email == savedEmail && password == savedPassword) {
                prefs.edit().putBoolean("loggedIn", true).apply()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Błędny login lub hasło", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
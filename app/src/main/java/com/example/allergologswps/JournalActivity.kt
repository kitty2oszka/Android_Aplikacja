package com.example.allergologswps

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.allergologswps.databinding.ActivityJournalBinding
import kotlinx.coroutines.launch

class JournalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)

        val nausea = findViewById<Switch>(R.id.switchNausea)
        val rash = findViewById<Switch>(R.id.switchRash)
        val vomiting = findViewById<Switch>(R.id.switchVomiting)
        val cough = findViewById<Switch>(R.id.switchCough)
        val swelling = findViewById<Switch>(R.id.switchSwelling)
        val note = findViewById<EditText>(R.id.noteEditText)
        val addButton = findViewById<Button>(R.id.addToJournalButton)
        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)

        val dao = AppDatabase.getDatabase(this).journalDao()

        bottomNav.selectedItemId = R.id.nav_journal
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_journal -> false // już jesteśmy
                R.id.nav_profile -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                else -> false
            }
        }

        addButton.setOnClickListener {
            val entry = JournalEntry(
                nausea = nausea.isChecked,
                rash = rash.isChecked,
                vomiting = vomiting.isChecked,
                cough = cough.isChecked,
                swelling = swelling.isChecked,
                note = note.text.toString(),
                timestamp = System.currentTimeMillis()
            )

            lifecycleScope.launch {
                dao.insert(entry)
                Toast.makeText(this@JournalActivity, "Dodano do dziennika", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
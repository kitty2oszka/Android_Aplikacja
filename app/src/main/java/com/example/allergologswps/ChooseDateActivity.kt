package com.example.allergologswps

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.allergologswps.AppDatabase
import com.example.allergologswps.DateEntity
import java.text.SimpleDateFormat
import java.util.*
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView

class ChooseDateActivity : AppCompatActivity() {

    private lateinit var dateTextView: TextView
    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_date)

        dateTextView = findViewById(R.id.dateTextView)
        val pickDateButton: Button = findViewById(R.id.pickDateButton)
        val saveButton: Button = findViewById(R.id.saveButton)
        val backButton: ImageView = findViewById(R.id.backButton)

        pickDateButton.setOnClickListener {
            showDatePicker()
        }

        saveButton.setOnClickListener {
            if (selectedDate.isNotEmpty()) {
                saveDateToDatabase(selectedDate)
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, day ->
                val cal = Calendar.getInstance()
                cal.set(year, month, day)
                val format = SimpleDateFormat("MM/dd/yyyy", Locale.US)
                selectedDate = format.format(cal.time)
                dateTextView.text = selectedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun saveDateToDatabase(date: String) {
        val dateEntity = DateEntity(date = date)
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getDatabase(this@ChooseDateActivity).dateDao().insertDate(dateEntity)
        }
    }
}

package com.example.allergologswps

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JournalEntry(
    val nausea: Boolean,
    val rash: Boolean,
    val vomiting: Boolean,
    val cough: Boolean,
    val swelling: Boolean,
    val note: String,
    val timestamp: Long,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

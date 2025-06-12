package com.example.allergologswps

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String
)


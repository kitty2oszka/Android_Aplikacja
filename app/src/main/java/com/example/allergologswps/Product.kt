package com.example.allergologswps

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val id: String,
    val name: String?,
    val imageUrl: String?
)

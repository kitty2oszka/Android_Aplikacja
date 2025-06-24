package com.example.allergologswps.models

import androidx.room.ColumnInfo

data class FrequentProduct(
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "count") val count: Int
)

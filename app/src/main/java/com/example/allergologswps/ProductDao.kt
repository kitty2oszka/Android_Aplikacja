package com.example.allergologswps

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ProductDao {

    @Insert
    suspend fun insert(product: Product)
}
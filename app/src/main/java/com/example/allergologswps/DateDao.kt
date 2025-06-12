package com.example.allergologswps

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DateDao {
    @Insert
    suspend fun insertDate(dateEntity: DateEntity)

    @Query("SELECT * FROM DateEntity")
    suspend fun getAllDates(): List<DateEntity>
}


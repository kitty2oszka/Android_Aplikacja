package com.example.allergologswps

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.allergologswps.models.FrequentProduct
import androidx.lifecycle.LiveData

@Dao
interface DateDao {
    @Insert
    suspend fun insertDate(dateEntity: DateEntity)


    @Query("SELECT * FROM DateEntity")
    suspend fun getAllDates(): List<DateEntity>

    @Query("""
        SELECT imageUrl, COUNT(imageUrl) as count
        FROM DateEntity
        GROUP BY imageUrl
        ORDER BY count DESC
    """)
    fun getMostFrequentProducts(): LiveData<List<FrequentProduct>>
}

package com.example.allergologswps

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JournalDao {
    @Insert
    suspend fun insert(entry: JournalEntry)

    @Query("SELECT * FROM JournalEntry ORDER BY timestamp DESC")
    suspend fun getAll(): List<JournalEntry>
}


package com.example.allergologswps.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.allergologswps.AppDatabase
import com.example.allergologswps.models.FrequentProduct

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val dateDao = AppDatabase.getDatabase(application).dateDao()

    val frequentProducts: LiveData<List<FrequentProduct>> = dateDao.getMostFrequentProducts()
}

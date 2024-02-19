package com.dicoding.asclepius.view.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.asclepius.data.local.AsclepiusDatabase

class HistoryViewModel(app: Application): AndroidViewModel(app) {
    private val db = AsclepiusDatabase.getInstance(app.applicationContext)
    private val dao = db.historyDao()

    val data = dao.getAll().asLiveData()
}
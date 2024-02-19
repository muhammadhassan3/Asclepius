package com.dicoding.asclepius.view.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.data.local.AsclepiusDatabase
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.utils.formatToString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class ResultViewModel(app: Application): AndroidViewModel(app) {
    private val historyDao by lazy {
        val db = AsclepiusDatabase.getInstance(app.applicationContext)
        db.historyDao()
    }


    fun saveRecord(uri: String, score: Float, result: String){
        val data = HistoryEntity(null, uri, result, score, Calendar.getInstance().time.formatToString())
        viewModelScope.launch(Dispatchers.IO) {
            historyDao.insert(data)
        }
    }
}
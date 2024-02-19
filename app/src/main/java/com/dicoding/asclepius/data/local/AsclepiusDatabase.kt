package com.dicoding.asclepius.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.asclepius.data.local.dao.HistoryDao
import com.dicoding.asclepius.data.local.entity.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1)
abstract class AsclepiusDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        @JvmStatic
        private var INSTANCE: AsclepiusDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(context, AsclepiusDatabase::class.java, "asclepius_db")
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }
}
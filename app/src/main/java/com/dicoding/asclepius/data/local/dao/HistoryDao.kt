package com.dicoding.asclepius.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
    fun insert(data: HistoryEntity)

    @Query("select * from tbl_history")
    fun getAll(): Flow<List<HistoryEntity>>
}
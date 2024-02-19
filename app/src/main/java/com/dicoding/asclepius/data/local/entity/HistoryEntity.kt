package com.dicoding.asclepius.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val uri: String,
    val result: String,
    val score: Float,
    val createdAt: String,
)
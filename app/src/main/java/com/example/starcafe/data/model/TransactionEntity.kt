package com.example.starcafe.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_history")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val description: String,
    val timestamp: Long,
    val stars: Int
)

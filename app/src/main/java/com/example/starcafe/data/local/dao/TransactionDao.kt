package com.example.starcafe.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.starcafe.data.model.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao : BaseDao<TransactionEntity> {

    @Query("SELECT * FROM transaction_history ORDER BY timestamp DESC")
    fun getAll(): Flow<List<TransactionEntity>>
}

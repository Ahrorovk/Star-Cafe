package com.example.starcafe.data

import com.example.starcafe.data.local.dao.TransactionDao
import com.example.starcafe.data.model.TransactionEntity
import javax.inject.Inject

class CoreRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) {
    suspend fun insert(transactionEntity: TransactionEntity) =
        transactionDao.insert(transactionEntity)

    fun getAll() = transactionDao.getAll()
}
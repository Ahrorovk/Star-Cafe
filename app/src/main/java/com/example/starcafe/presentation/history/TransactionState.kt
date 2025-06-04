package com.example.starcafe.presentation.history

import com.example.starcafe.data.model.TransactionEntity

data class TransactionState(
    val transactions: List<TransactionEntity> = emptyList()
)
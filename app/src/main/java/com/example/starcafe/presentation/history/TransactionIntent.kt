package com.example.starcafe.presentation.history

sealed class TransactionIntent {
    object LoadTransactions : TransactionIntent()
}
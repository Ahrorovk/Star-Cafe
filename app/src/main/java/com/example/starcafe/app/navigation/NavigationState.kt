package com.example.starcafe.app.navigation

import com.example.starcafe.data.model.TransactionEntity

data class NavigationState(
    val tokenState: String = "",
    val getAll: List<TransactionEntity> = emptyList()
)
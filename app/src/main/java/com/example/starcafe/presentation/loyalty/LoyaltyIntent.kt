package com.example.starcafe.presentation.loyalty

sealed class LoyaltyIntent {
    data class SetTotalSpent(val amount: Int) : LoyaltyIntent()
}

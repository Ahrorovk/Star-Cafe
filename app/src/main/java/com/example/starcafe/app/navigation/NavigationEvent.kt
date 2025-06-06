package com.example.starcafe.app.navigation

sealed class NavigationEvent {
    object OnTokenStateChange : NavigationEvent()
    object OnTransactionHistoryChange : NavigationEvent()
}
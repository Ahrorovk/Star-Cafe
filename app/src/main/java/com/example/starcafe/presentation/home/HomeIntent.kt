package com.example.starcafe.presentation.home

sealed class HomeIntent {
    object LoadInitialData : HomeIntent()
    object OnAddStarsClicked : HomeIntent()
    object OnDismissDialog : HomeIntent()
    data class OnInputStarsChanged(val value: String) : HomeIntent()
    object OnConfirmAddStars : HomeIntent()
}

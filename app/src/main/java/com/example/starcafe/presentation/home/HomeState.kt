package com.example.starcafe.presentation.home

data class HomeState(
    val randomNumber: Int? = null,
    val qrCode: String = "",
    val showDialog: Boolean = false,
    val starBalance: Int? = null,
    val loyaltyLevel: String = "",
    val inputStars: String = ""
)
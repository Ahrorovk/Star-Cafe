package com.example.starcafe.core

sealed class Routes(val route: String) {
    object Home : Routes("Home")
    object Splash : Routes("Splash")
    object Welcome : Routes("Welcome")
    object Rewards : Routes("Rewards")
    object Menu : Routes("Menu")
    object Level : Routes("Level")
    object History : Routes("History")
    object Profile : Routes("Profile")
    object SpecialOffers : Routes("Special Offers")
    object Contact : Routes("Contact")
    object Instruction : Routes("Instruction")
    object Loyalty : Routes("Loyalty")
}
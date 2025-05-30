package com.example.starcafe.core

sealed class Routes(val route: String) {
    object Home:Routes("home")
    object Splash:Routes("splash")
    object Welcome:Routes("welcome")
    object Rewards:Routes("rewards")
    object Menu:Routes("menu")
    object History:Routes("history")
    object Profile:Routes("profile")
    object SpecialOffers:Routes("special_offers")
    object Contact:Routes("contact")
}
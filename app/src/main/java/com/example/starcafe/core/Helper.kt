package com.example.starcafe.core

fun doesScreenHasBottomBar(currentScreen: String): Boolean =
    currentScreen != Routes.Splash.route &&
            currentScreen != Routes.Welcome.route &&
            currentScreen != Routes.Contact.route &&
            currentScreen != Routes.History.route &&
            currentScreen != Routes.Profile.route
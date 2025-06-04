package com.example.starcafe.core

fun doesScreenHasBottomBar(currentScreen: String): Boolean =
    currentScreen != Routes.Splash.route &&
            currentScreen != Routes.Welcome.route &&
            currentScreen != Routes.Contact.route &&
            currentScreen != Routes.History.route &&
            currentScreen != Routes.Profile.route

fun doesScreenHasTopAppBar(currentScreen: String): Boolean =
    currentScreen != Routes.Splash.route &&
            currentScreen != Routes.Welcome.route

fun getTopBarTitle(currentScreen: String): String =
    if (doesScreenHasBottomBar(currentScreen))
        currentScreen else ""
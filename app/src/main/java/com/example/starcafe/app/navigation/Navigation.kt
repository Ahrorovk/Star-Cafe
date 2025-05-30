package com.example.starcafe.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahrorovk.labwork.app.navigation.components.StarCafeBottomBar
import com.example.starcafe.core.Routes
import com.example.starcafe.core.doesScreenHasBottomBar
import com.example.starcafe.presentation.home.HomeScreen
import com.example.starcafe.presentation.splash.SplashScreen
import com.example.starcafe.presentation.welcome.WelcomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""
    Scaffold(bottomBar = {
        if (doesScreenHasBottomBar(currentScreen)) {
            StarCafeBottomBar(navController)
        }
    }) { it_ ->
        NavHost(
            navController = navController,
            startDestination = Routes.Splash.route,
            modifier = Modifier.padding(it_)
        ) {
            composable(Routes.Splash.route) { SplashScreen(navController) }
            composable(Routes.Welcome.route) { WelcomeScreen(navController) }
            composable(Routes.Home.route) { HomeScreen(navController) }
            /*composable(Routes.Rewards.route) { RewardsScreen(navController) }
            composable(Routes.Menu.route) { MenuScreen(navController) }
            composable(Routes.History.route) { HistoryScreen(navController) }
            composable(Routes.Profile.route) { ProfileScreen(navController) }
            composable(Routes.SpecialOffers.route) { SpecialOffersScreen(navController) }
            composable(Routes.Contact.route) { ContactLocationsScreen(navController) }*/
        }
    }
}


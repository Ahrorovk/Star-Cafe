package com.example.starcafe.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahrorovk.labwork.app.navigation.components.StarCafeBottomBar
import com.example.starcafe.components.MenuTopBar
import com.example.starcafe.components.ProfileTopBar
import com.example.starcafe.components.RewardsTopBar
import com.example.starcafe.components.SpecialOfferTopBar
import com.example.starcafe.core.Routes
import com.example.starcafe.presentation.home.HomeScreen
import com.example.starcafe.presentation.home.HomeViewModel
import com.example.starcafe.presentation.menu.MenuScreen
import com.example.starcafe.presentation.menu.MenuViewModel
import com.example.starcafe.presentation.rewards.RewardsScreen
import com.example.starcafe.presentation.rewards.RewardsViewModel
import com.example.starcafe.presentation.splash.SplashScreen
import com.example.starcafe.presentation.welcome.WelcomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""

    val bottomBarScreens = listOf(
        Routes.Home.route,
        Routes.Menu.route,
        Routes.Rewards.route,
        Routes.SpecialOffers.route,
        Routes.Profile.route,
        Routes.History.route
    )

    val rewardTopBarScreen = listOf(
        Routes.Rewards.route
    )
    val menuTopBarScreen = listOf(
        Routes.Menu.route
    )
    val profileTopBarScreen = listOf(
        Routes.Profile.route,
        Routes.History.route,
        Routes.Contact.route,
        Routes.Level.route
    )
    val specialOfferTopBarScreen = listOf(
        Routes.SpecialOffers.route
    )

    Scaffold(
        topBar = {
            when (currentScreen) {
                in rewardTopBarScreen -> RewardsTopBar()
                in menuTopBarScreen -> MenuTopBar()
                in profileTopBarScreen -> ProfileTopBar(navController)
                in specialOfferTopBarScreen -> SpecialOfferTopBar()
            }
        },
        bottomBar = {
            if (currentScreen in bottomBarScreens) {
                StarCafeBottomBar(navController, modifier = Modifier.padding(bottom = 50.dp))
            }
        },
        containerColor = Color.Black
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.Splash.route) { SplashScreen(navController) }
            composable(Routes.Welcome.route) { WelcomeScreen(navController) }
            composable(Routes.Home.route) { HomeScreen(navController, hiltViewModel<HomeViewModel>()) }
            composable(Routes.Rewards.route) { RewardsScreen(viewModel = hiltViewModel<RewardsViewModel>()) }
            composable(Routes.Menu.route) { MenuScreen(viewModel = hiltViewModel<MenuViewModel>()) }
            /*composable(Routes.History.route) { HistoryScreen(navController) }
            composable(Routes.Profile.route) { ProfileScreen(navController) }
            composable(Routes.SpecialOffers.route) { SpecialOffersScreen(navController) }
            composable(Routes.Contact.route) { ContactLocationsScreen(navController) }*/
        }
    }
}



package com.ahrorovk.labwork.app.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.NavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.ui.graphics.Color
import com.example.starcafe.components.BottomNavItem
import com.example.starcafe.core.Routes
import com.example.starcafe.data.model.BottomNavDestination

@Composable
fun StarCafeBottomBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.Gray
    ) {
        bottomNavDestinations.forEach { navItem ->
            BottomNavItem(navController = navController, item = navItem)
        }

    }
}

val bottomNavDestinations = listOf(
    BottomNavDestination(
        label = "Home",
        destinationRoute = Routes.Home.route,
        icon = Icons.Default.Home
    ),
    BottomNavDestination(
        label = "Rewards",
        destinationRoute = Routes.Rewards.route,
        icon = Icons.Default.CardGiftcard
    ),
    BottomNavDestination(
        label = "Menu",
        destinationRoute = Routes.Menu.route,
        icon = Icons.Default.LocalCafe
    ),
    BottomNavDestination(
        label = "Special Offers",
        destinationRoute = Routes.SpecialOffers.route,
        icon = Icons.Default.Diamond
    ),
)
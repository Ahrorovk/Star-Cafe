package com.ahrorovk.labwork.app.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.NavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Divider
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.starcafe.R
import com.example.starcafe.components.BottomNavItem
import com.example.starcafe.core.Routes
import com.example.starcafe.data.model.BottomNavDestination

@Composable
fun StarCafeBottomBar(navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        BottomNavigation(
            backgroundColor = Color.Black,
            contentColor = Color.Gray,
        ) {
            bottomNavDestinations.forEach { navItem ->
                BottomNavItem(navController = navController, item = navItem)
            }
        }
    }
}


val bottomNavDestinations = listOf(
    BottomNavDestination(
        label = "Home",
        destinationRoute = Routes.Home.route,
        icon = R.drawable.home
    ),
    BottomNavDestination(
        label = "Rewards",
        destinationRoute = Routes.Rewards.route,
        icon = R.drawable.gift
    ),
    BottomNavDestination(
        label = "Menu",
        destinationRoute = Routes.Menu.route,
        icon = R.drawable.tea
    ),
    BottomNavDestination(
        label = "Special Offers",
        destinationRoute = Routes.SpecialOffers.route,
        icon = R.drawable.diamond
    ),
)
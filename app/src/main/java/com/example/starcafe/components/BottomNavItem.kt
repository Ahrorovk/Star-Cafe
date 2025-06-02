package com.example.starcafe.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.starcafe.core.Routes
import com.example.starcafe.data.model.BottomNavDestination

@Composable
fun RowScope.BottomNavItem(
    navController: NavController,
    item: BottomNavDestination
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any {
            when (item.destinationRoute) {
                Routes.Home.route -> {
                    it.route == Routes.Home.route
                }

                Routes.Rewards.route -> {
                    it.route == Routes.Rewards.route
                }
                Routes.Menu.route -> {
                    it.route == Routes.Menu.route
                }
                Routes.SpecialOffers.route -> {
                    it.route == Routes.SpecialOffers.route
                }

                else -> {
                    it.route == Routes.Home.route
                }
            }
        } == true,
        onClick = {
            if (currentDestination?.route != item.destinationRoute)
                navigateToScreen(item.destinationRoute, navController)
        },
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = item.icon,
                contentDescription = "BottomNavIcon",
                tint = Color(0xFF999999)
            )
        },
        label = {
            Text(
                text = item.label,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )
        },
        alwaysShowLabel = true,
        selectedContentColor = Color(0xFFFFA726),
        unselectedContentColor = Color.Gray
    )
}

private fun navigateToScreen(route: String, navController: NavController) {
    navController.navigate(route = route) {
        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
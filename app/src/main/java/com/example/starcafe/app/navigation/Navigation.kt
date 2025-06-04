@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.starcafe.app.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahrorovk.labwork.app.navigation.components.StarCafeBottomBar
import com.example.starcafe.core.Routes
import com.example.starcafe.core.doesScreenHasBottomBar
import com.example.starcafe.core.doesScreenHasTopAppBar
import com.example.starcafe.core.getTopBarTitle
import com.example.starcafe.presentation.contact.ContactLocationScreen
import com.example.starcafe.presentation.history.TransactionHistoryScreen
import com.example.starcafe.presentation.history.TransactionViewModel
import com.example.starcafe.presentation.home.HomeScreen
import com.example.starcafe.presentation.home.HomeViewModel
import com.example.starcafe.presentation.instruction.InstructionScreen
import com.example.starcafe.presentation.loyalty.LoyaltyLevelScreen
import com.example.starcafe.presentation.loyalty.LoyaltyViewModel
import com.example.starcafe.presentation.menu.MenuScreen
import com.example.starcafe.presentation.menu.MenuViewModel
import com.example.starcafe.presentation.profile.ProfileScreen
import com.example.starcafe.presentation.rewards.RewardsScreen
import com.example.starcafe.presentation.rewards.RewardsViewModel
import com.example.starcafe.presentation.special.offers.SpecialOfferScreen
import com.example.starcafe.presentation.splash.SplashScreen
import com.example.starcafe.presentation.welcome.WelcomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""

    Scaffold(
        topBar = {
            if (doesScreenHasTopAppBar(currentScreen)) {
                TopAppBar(
                    title = {
                        Text(
                            getTopBarTitle(currentScreen),
                            fontSize = 22.sp
                        )
                    },
                    navigationIcon = {
                        if (!doesScreenHasBottomBar(currentScreen)) {
                            IconButton({
                                navController.popBackStack()
                            }) {
                                Row(Modifier, verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.ArrowBackIosNew,
                                        contentDescription = "ArrowBackIosNew"
                                    )

                                    Spacer(Modifier.padding(3.dp))

                                    Text("Back", fontSize = 14.sp)
                                }
                            }
                        }
                    },
                    actions = {
                        if (currentScreen == Routes.Home.route) {
                            IconButton({
                                navController.navigate(Routes.Profile.route)
                            }) {
                                Icon(Icons.Default.Person, contentDescription = "Person")
                            }
                        }
                    }
                )

            }
        },
        bottomBar = {
            if (doesScreenHasBottomBar(currentScreen)) {
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
            composable(Routes.Home.route) {
                HomeScreen(
                    navController,
                    hiltViewModel<HomeViewModel>()
                )
            }
            composable(Routes.Rewards.route) {
                val viewModel = hiltViewModel<RewardsViewModel>()
                val state = viewModel.state.collectAsState()
                RewardsScreen(state.value) { intent ->
                    when (intent) {
                        else -> viewModel.onEvent(intent)
                    }
                }
            }
            composable(Routes.Menu.route) { MenuScreen(viewModel = hiltViewModel<MenuViewModel>()) }
            composable(Routes.Profile.route) { ProfileScreen(navController) }
            composable(Routes.Instruction.route) { InstructionScreen() }
            composable(Routes.Contact.route) { ContactLocationScreen() }
            composable(Routes.SpecialOffers.route) { SpecialOfferScreen() }
            composable(Routes.History.route) {
                val viewModel = hiltViewModel<TransactionViewModel>()
                val state = viewModel.state.collectAsState()
                TransactionHistoryScreen(state.value) { event ->
                    when (event) {
                        else -> viewModel.onEvent(event)
                    }
                }
            }
            composable(Routes.Level.route) {
                val viewModel = hiltViewModel<LoyaltyViewModel>()
                val state = viewModel.state.collectAsState()
                LoyaltyLevelScreen(state.value) { event ->
                    when (event) {
                        else -> viewModel.onEvent(event)
                    }
                }
            }
        }
    }
}



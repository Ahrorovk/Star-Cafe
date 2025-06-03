package com.example.starcafe.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starcafe.core.Routes

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

    }
}

val menuItems = listOf(
    "Your Loyalty Level" to Routes.Level.route,
    "Transaction History" to Routes.History.route,
    "How it works" to Routes.Instruction.route,
    "Contact & Locations" to Routes.Contact.route
)
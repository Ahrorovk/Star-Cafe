package com.example.starcafe.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starcafe.components.ProfileMenuItem
import com.example.starcafe.core.Routes

@Composable
fun ProfileScreen(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        menuItems.forEach { (title, route) ->
            ProfileMenuItem(
                title,
                onClick = { onNavigate(route) }
            )
        }
    }
}

val menuItems = listOf(
    "Your Loyalty Level" to Routes.Level.route,
    "Transaction History" to Routes.History.route,
    "How it works" to Routes.Instruction.route,
    "Contact & Locations" to Routes.Contact.route
)
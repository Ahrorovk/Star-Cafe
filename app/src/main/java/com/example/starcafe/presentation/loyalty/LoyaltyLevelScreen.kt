package com.example.starcafe.presentation.loyalty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starcafe.components.LoyaltyCard

@Composable
fun LoyaltyLevelScreen(
    state: LoyaltyState,
    onIntent: (LoyaltyIntent) -> Unit
) {

    val level = when {
        state.totalSpent > 5000 -> "Gold"
        state.totalSpent >= 1001 -> "Silver"
        else -> "Bronze"
    }
    val progressToNext = when (level) {
        "Bronze" -> 1000 - state.totalSpent
        "Silver" -> 5000 - state.totalSpent
        else -> 0
    }
    val cashback = when (level) {
        "Gold" -> "7% cashback"
        "Silver" -> "5% cashback"
        else -> "3% cashback"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Your Loyalty Level", color = Color.White, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Text("Silver", color = Color.Gray)
            Text("Gold", color = Color.Yellow)
        }
        LinearProgressIndicator(
            progress = state.totalSpent / 5000f,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color(0xFFFFA726)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text("$cashback", color = Color.White, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        LoyaltyCard("Bronze", "up to 1000 units", "3%")
        LoyaltyCard("Silver", "1001–5000 units", "5%")
        LoyaltyCard("Gold", "over 5000 units", "7%")
        Spacer(modifier = Modifier.height(24.dp))
        Text("Your Progress", color = Color.White, fontSize = 18.sp)
        Text("You've currently spent ${state.totalSpent} units.", color = Color.White)
        if (level != "Gold") {
            Text(
                "Spend $progressToNext more units to reach the next level and increase your cashback!",
                color = Color.Gray
            )
        }
    }
}

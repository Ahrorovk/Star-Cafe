package com.example.starcafe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoyaltyCard(level: String, range: String, cashback: String) {
    val iconColor = when (level) {
        "Bronze" -> Color(0xFFCD7F32)
        "Silver" -> Color.LightGray
        "Gold" -> Color(0xFFFFD700)
        else -> Color.White
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Star, contentDescription = null, tint = iconColor)
            Spacer(modifier = Modifier.width(8.dp))
            Text("$level Level", color = Color.White, fontSize = 16.sp)
        }
        Text("Total spent: $range", color = Color.Gray)
        Text("Cashback: $cashback of each purchase", color = Color.Gray)
    }
}

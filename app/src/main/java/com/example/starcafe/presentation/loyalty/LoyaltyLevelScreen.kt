package com.example.starcafe.presentation.loyalty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starcafe.R
import com.example.starcafe.components.LoyaltyCard


data class LoyaltyLevelDisplayState(
    val currentLevel: String,
    val currentIcon: Int,
    val currentCashback: String,
    val progress: Pair<Float, Int>,
    val nextLevel: String,
    val nextCashback: String,
    val nextIcon: Int
)

@Composable
fun LoyaltyLevelScreen(
    state: LoyaltyState,
    onIntent: (LoyaltyIntent) -> Unit
) {
    val displayState = calculateLevelProgress(state.totalSpent)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(displayState.currentIcon),
                        contentDescription = null,
                        tint = when (displayState.currentLevel) {
                            "Gold" -> Color(0xFFFFD700)
                            "Silver" -> Color.LightGray
                            "Bronze" -> Color(0xFFCD7F32)
                            else -> Color.Gray
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(displayState.currentLevel, color = Color.White, fontSize = 14.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(displayState.nextIcon),
                        contentDescription = null,
                        tint = when (displayState.nextLevel) {
                            "Gold" -> Color(0xFFFFD700)
                            "Silver" -> Color.LightGray
                            "Bronze" -> Color(0xFFCD7F32)
                            else -> Color.Gray
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(displayState.nextLevel, color = Color.Gray, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = displayState.progress.first.coerceIn(0f, 1f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = Color(0xFFFFA726)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(displayState.currentCashback, color = Color.White, fontSize = 14.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(displayState.nextCashback, color = Color.Gray, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Your current level determines how many stars you earn on each purchase.Spend more to upgrade your level and earn more cashback!",
                color = Color.White,
                fontSize = 18.sp, textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            LoyaltyCard("Bronze", "up to 1000 units", "3%")
            Spacer(modifier = Modifier.height(16.dp))
            LoyaltyCard("Silver", "1001–5000 units", "5%")
            Spacer(modifier = Modifier.height(16.dp))
            LoyaltyCard("Gold", "over 5000 units units", "7%")

            Spacer(modifier = Modifier.height(24.dp))

            Text("Your Progress", color = Color.White, fontSize = 18.sp)
            Text("You've currently spent ${state.totalSpent} units.", color = Color.White)

            if (displayState.currentLevel != "Gold") {
                Text(
                    "Spend ${displayState.progress.second} more units to reach the next level and increase your cashback!",
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Keep earning stars and enjoy exclusive rewards at STAR CAFE!",
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
fun calculateLevelProgress(totalSpent: Int): LoyaltyLevelDisplayState {
    val stages = listOf(0, 1000, 5000, 5000) // Starter, Bronze, Silver, Gold
    val stageIndex = stages.indexOfLast { totalSpent >= it }

    val currentStart = stages.getOrElse(stageIndex) { 0 }
    val nextTarget = stages.getOrElse(stageIndex + 1) { currentStart }
    val progress = if (nextTarget != currentStart) {
        (totalSpent - currentStart).toFloat() / (nextTarget - currentStart)
    } else 1f

    val (level, icon, cashback) = when {
        totalSpent > 5000 -> Triple("Gold", R.drawable.level_gold, "7% cashback")
        totalSpent in 1001..5000 -> Triple("Silver", R.drawable.loyalty_level, "5% cashback")
        totalSpent in 1..1000 -> Triple("Bronze", R.drawable.level_bronze, "3% cashback")
        else -> Triple("None", R.drawable.loyalty_level, "0% cashback")
    }

    val (nextLevel, nextCashback, nextIcon) = when (level) {
        "Gold" -> Triple("Max", "", R.drawable.level_gold)
        "Silver" -> Triple("Gold", "7% cashback", R.drawable.level_gold)
        "Bronze" -> Triple("Silver", "5% cashback", R.drawable.loyalty_level)
        "None" -> Triple("Bronze", "3% cashback", R.drawable.level_bronze)
        else -> Triple("", "", R.drawable.level_gold)
    }

    return LoyaltyLevelDisplayState(
        currentLevel = level,
        currentIcon = icon,
        currentCashback = cashback,
        progress = progress.coerceIn(0f, 1f) to (nextTarget - totalSpent).coerceAtLeast(0),
        nextLevel = nextLevel,
        nextCashback = nextCashback,
        nextIcon = nextIcon
    )
}


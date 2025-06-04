package com.example.starcafe.presentation.rewards

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.starcafe.components.RedeemedDialog
import com.example.starcafe.components.RewardItemCard
import com.example.starcafe.data.model.RewardItem
import kotlinx.coroutines.delay

@Composable
fun RewardsScreen(state: RewardsState, onIntent: (RewardsIntent) -> Unit) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.reward) { item ->
            RewardItemCard(
                item = item,
                onClick = {
                    onIntent(RewardsIntent.OnRewardClick(item))
                    onIntent(RewardsIntent.OnRemoveStarClick(item))
                }
            )
        }
    }

    LaunchedEffect(state.error) {
        if (state.error.isNotEmpty()) {
            Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
            delay(3000)
            onIntent(RewardsIntent.OnErrorChange(""))
        }
    }
    if (state.isDialogVisible && state.selectedItem != null) {
        RedeemedDialog(
            onDismiss = {
                onIntent(RewardsIntent.DismissDialog)
            },
            redeemedItem = state.selectedItem
        )
    }
}
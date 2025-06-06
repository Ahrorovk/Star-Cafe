package com.example.starcafe.presentation.rewards

import com.example.starcafe.data.model.RewardItem

data class RewardsState(
    val reward: List<RewardItem> = emptyList(),
    val selectedItem: RewardItem? = null,
    val isDialogVisible: Boolean = false,
    val error: String = "",
    val totalScore: Int = 0
)
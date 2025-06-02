package com.example.starcafe.presentation.rewards

import com.example.starcafe.data.model.RewardItem

sealed class RewardsIntent {
    object LoadRewards : RewardsIntent()
    data class OnRewardClick(val item: RewardItem) : RewardsIntent()
    data class OnRemoveStarClick(val item: RewardItem): RewardsIntent()
    object DismissDialog : RewardsIntent()
}

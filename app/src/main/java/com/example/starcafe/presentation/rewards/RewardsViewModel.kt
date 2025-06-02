package com.example.starcafe.presentation.rewards

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starcafe.R
import com.example.starcafe.data.local.dataStore.DataStoreManager
import com.example.starcafe.data.model.RewardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor(@ApplicationContext private val context: Context) : ViewModel() {
    private val _state = MutableStateFlow(
        RewardsState(
            reward = listOf(
                RewardItem("honey-cake", "Honey Cake", "250", R.drawable.honey_cake),
                RewardItem("coconut-eclair", "Coconut Éclair", "200", R.drawable.coconut_eclair),
                RewardItem("pastrami-melt", "Pastrami Melt", "640", R.drawable.pastrami_melt),
                RewardItem("latte", "Latte", "250", R.drawable.latte),
                RewardItem("loose-leaf-tea", "Loose Leaf Tea", "200", R.drawable.loose_leaf_tea)
            )
        )
    )
    val state = _state
    val dataStore = DataStoreManager(context)

    fun onEvent(event: RewardsIntent) {
        when (event) {
            is RewardsIntent.OnRewardClick -> {
                _state.update {
                    it.copy(
                        selectedItem = event.item,
                        isDialogVisible = true
                    )
                }
            }

            is RewardsIntent.DismissDialog -> {
                _state.update {
                    it.copy(
                        selectedItem = null,
                        isDialogVisible = false
                    )
                }
            }

            is RewardsIntent.OnRemoveStarClick -> {
                viewModelScope.launch {
                    val cost = event.item.points.toIntOrNull() ?: 0
                    val success = dataStore.spendStars(cost)

                    if (success) {
                        dataStore.increaseTotalSpentStars(cost)

//                        _state.value = _state.value.copy(
//                            selectedItem = null
//                        )
                    } else {
                        // Если не хватает звёзд — можно показать сообщение или оставить диалог
                        _state.update {
                            it.copy(
                                // может быть: error = "Not enough stars"
                            )
                        }
                    }
                }
            }
            else -> {}
        }
    }
}
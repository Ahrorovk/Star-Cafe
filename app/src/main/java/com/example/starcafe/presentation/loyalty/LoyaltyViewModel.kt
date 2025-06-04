package com.example.starcafe.presentation.loyalty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starcafe.data.local.dataStore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class LoyaltyViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val _state = MutableStateFlow(LoyaltyState())
    val state: StateFlow<LoyaltyState> = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        LoyaltyState()
    )

    init {
        dataStoreManager.getTotalSpentStars().onEach { value ->
            _state.update {
                it.copy(
                    totalSpent = value
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(intent: LoyaltyIntent) {
        when (intent) {
            is LoyaltyIntent.SetTotalSpent -> {
                _state.update { it.copy(totalSpent = intent.amount) }
            }
        }
    }
}

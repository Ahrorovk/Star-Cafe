package com.example.starcafe.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starcafe.data.CoreRepositoryImpl
import com.example.starcafe.data.local.dataStore.DataStoreManager
import com.example.starcafe.data.model.TransactionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val coreRepositoryImpl: CoreRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        HomeState()
    )


    init {
        observeStarBalance()
        onEvent(HomeIntent.LoadInitialData)
    }

    fun onEvent(event: HomeIntent) {
        when (event) {
            is HomeIntent.LoadInitialData -> {
                viewModelScope.launch {
                    dataStoreManager.getTotalSpentStars().onEach { total ->
                        _state.update {
                            it.copy(
                                randomNumber = dataStoreManager.getOrGenerateRandomNumber(),
                                qrCode = Random.nextLong(1000000000L, 9999999999L).toString(),
                                loyaltyLevel = calculateLoyaltyLevel(total)
                            )
                        }
                    }.launchIn(viewModelScope)
                }
            }

            is HomeIntent.OnAddStarsClicked -> {
                _state.update { it.copy(showDialog = true) }
            }

            is HomeIntent.OnDismissDialog -> {
                _state.update { it.copy(showDialog = false) }
            }

            is HomeIntent.OnInputStarsChanged -> {
                _state.update { it.copy(inputStars = event.value) }
            }

            is HomeIntent.OnConfirmAddStars -> {
                val starsToAdd = _state.value.inputStars.toIntOrNull()
                if (starsToAdd != null && starsToAdd > 0) {
                    viewModelScope.launch {
                        dataStoreManager.addStars(starsToAdd)
                        _state.update {
                            it.copy(
                                inputStars = "",
                                showDialog = false
                            )
                        }
                        coreRepositoryImpl.insert(
                            TransactionEntity(
                                null,
                                "Stars earned at STAR CAFE",
                                System.currentTimeMillis(),
                                starsToAdd
                            )
                        )
                    }
                }
            }

            else -> {}
        }
    }

    private fun observeStarBalance() {
        viewModelScope.launch {
            dataStoreManager.getStarBalance().collect { balance ->
                _state.value = _state.value.copy(starBalance = balance)
            }
        }
    }

    private fun calculateLoyaltyLevel(totalSpent: Int): String {
        return when {
            totalSpent < 1 -> "Starter"
            totalSpent <= 1000 -> "Bronze"
            totalSpent <= 5000 -> "Silver"
            totalSpent > 5000 -> "Gold"
            else -> "Starter"
        }
    }
}

package com.example.starcafe.presentation.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starcafe.data.local.dataStore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        HomeState()
    )

    private val dataStore = DataStoreManager(context)

    init {
        observeStarBalance() // <-- Подписка на изменения баланса
        onEvent(HomeIntent.LoadInitialData)
    }

    fun onEvent(event: HomeIntent) {
        when (event) {
            is HomeIntent.LoadInitialData -> {
                viewModelScope.launch {
                    val randomNum = dataStore.getOrGenerateRandomNumber()
                    val totalSpent = dataStore.getTotalSpentStars()
                    val level = calculateLoyaltyLevel(totalSpent)
                    val code = Random.nextLong(1000000000L, 9999999999L).toString()
                    _state.update {
                        it.copy(
                            randomNumber = randomNum,
                            qrCode = code,
                            loyaltyLevel = level
                        )
                    }
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
                        dataStore.addStars(starsToAdd)
                        _state.update {
                            it.copy(
                            inputStars = "",
                            showDialog = false
                        ) }
                        // баланс обновится автоматически через observeStarBalance
                    }
                }
            }
        }
    }

    private fun observeStarBalance() {
        viewModelScope.launch {
            dataStore.getStarBalance().collect { balance ->
                _state.value = _state.value.copy(starBalance = balance)
            }
        }
    }

    private suspend fun calculateLoyaltyLevel(totalSpent: Flow<Int>): String {
        val value = totalSpent.first() // Получаем первое значение из потока
        return when {
            value >= 3000 -> "Gold"
            value >= 2000 -> "Silver"
            value >= 1000 -> "Bronze"
            else -> "Starter"
        }
    }
}

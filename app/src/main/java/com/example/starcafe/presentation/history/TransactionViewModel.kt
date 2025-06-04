package com.example.starcafe.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starcafe.data.CoreRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: CoreRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(TransactionState())
    val state: StateFlow<TransactionState> = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        TransactionState()
    )

    fun onEvent(intent: TransactionIntent) {
        when (intent) {
            is TransactionIntent.LoadTransactions -> {
                viewModelScope.launch {
                    repository.getAll().collect { result ->
                        _state.update { it.copy(transactions = result) }
                    }
                }
            }
        }
    }
}
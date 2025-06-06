package com.example.starcafe.app.navigation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starcafe.data.CoreRepositoryImpl
import com.example.starcafe.data.local.dataStore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationVewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val coreRepositoryImpl: CoreRepositoryImpl
) : ViewModel() {
    private val _state = MutableStateFlow(NavigationState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        NavigationState()
    )

    init {
        dataStoreManager.getTokenState.onEach { value ->
            _state.update {
                it.copy(
                    tokenState = value
                )
            }
            Log.e("TAG", "istoken->$value -- ${_state.value.tokenState}")
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: NavigationEvent) {
        when (event) {
            NavigationEvent.OnTokenStateChange -> {
                viewModelScope.launch {
                    dataStoreManager.updateTokenState("isAlreadyOpened")

                }
            }

            NavigationEvent.OnTransactionHistoryChange -> {
                coreRepositoryImpl.getAll().onEach { result ->
                    _state.update {
                        it.copy(
                            getAll = result
                        )
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}
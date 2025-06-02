package com.example.starcafe.presentation.menu

import android.media.metrics.Event
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starcafe.components.MenuFakeItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(MenuState(manuItem = MenuFakeItems.menuItems))
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        MenuState()
    )

    fun onEvent(event: MenuIntent) {
        when(event) {
            is MenuIntent.SelectCategory -> {
                _state.update {
                    it.copy(
                        selectedCategory = event.category
                    )
                }
            }
        }
    }
}
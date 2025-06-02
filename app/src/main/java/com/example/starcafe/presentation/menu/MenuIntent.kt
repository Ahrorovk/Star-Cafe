package com.example.starcafe.presentation.menu

import com.example.starcafe.data.model.MenuCategory

sealed class MenuIntent {
    data class SelectCategory(val category: MenuCategory) : MenuIntent()
}
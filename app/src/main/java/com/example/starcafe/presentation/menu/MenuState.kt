package com.example.starcafe.presentation.menu

import com.example.starcafe.data.model.MenuCategory
import com.example.starcafe.data.model.MenuItem

data class MenuState(
    val selectedCategory: MenuCategory = MenuCategory.COFFEE,
    val manuItem: List<MenuItem> = emptyList()
)

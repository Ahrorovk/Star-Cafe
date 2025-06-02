package com.example.starcafe.data.model

import com.example.starcafe.R

data class MenuItem(
    val name: String,
    val price: String,
    val volume: String,
    val description: String,
    val imageResId: Int,
    val category: MenuCategory
)

enum class MenuCategory(val iconResId: Int) {
    COFFEE(R.drawable.coffee),
    TEA(R.drawable.tea),
    DESSERTS(R.drawable.dessert),
    SNACKS(R.drawable.snacks)
}

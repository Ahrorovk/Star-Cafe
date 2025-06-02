package com.example.starcafe.components

import com.example.starcafe.R
import com.example.starcafe.data.model.MenuCategory
import com.example.starcafe.data.model.MenuItem

object MenuFakeItems {
    val menuItems = listOf(
        MenuItem(
            name = "Cappuccino",
            price = "$2.00",
            description = "Classic cappuccino with rich espresso and velvety steamed milk.",
            volume = "150 ml",
            category = MenuCategory.COFFEE,
            imageResId = R.drawable.cappucino
        ),
        MenuItem(
            name = "Latte",
            price = "$2.50",
            description = "Smooth espresso blended with steamed milk for a creamy, balanced taste.",
            volume = "300 ml",
            category = MenuCategory.COFFEE,
            imageResId = R.drawable.latte
        ),
        MenuItem(
            name = "Filter Coffee",
            price = "$2.80",
            description = "Brewed black coffee with a clean and smooth taste, served hot.",
            volume = "400 ml",
            category = MenuCategory.COFFEE,
            imageResId = R.drawable.filter_coffee
        ),
        MenuItem(
            name = "Espresso",
            price = "$1.80",
            description = "Strong and intense shot of pure espresso with a rich crema.",
            volume = "20 ml",
            category = MenuCategory.COFFEE,
            imageResId = R.drawable.expresso
        ),
        MenuItem(
            name = "Flat White",
            price = "$2.20",
            description = "A balanced blend of espresso and", // Описание обрезано на изображении
            volume = "150 ml",
            category = MenuCategory.COFFEE,
            imageResId = R.drawable.flat_white
        ),

        // Tea
        MenuItem(
            name = "Loose Leaf Tea",
            price = "$2.00",
            description = "Aromatic loose leaf tea, carefully brewed for a rich and natural flavor.",
            volume = "400 ml",
            category = MenuCategory.TEA,
            imageResId = R.drawable.loose_leaf_tea
        ),
        MenuItem(
            name = "Matcha Latte",
            price = "$3.20",
            description = "Creamy latte made with premium Japanese matcha and steamed milk.",
            volume = "300 ml",
            category = MenuCategory.TEA,
            imageResId = R.drawable.matcha_latte
        ),

        // Desserts
        MenuItem(
            name = "Honey Cake",
            price = "$2.50",
            description = "Wheat flour, chicken egg, sugar, honey, butter (82%), baking soda, sour cream, salt",
            volume = "150 g",
            category = MenuCategory.DESSERTS,
            imageResId = R.drawable.honey_cake
        ),
        MenuItem(
            name = "Chocolate Cheesecake",
            price = "$3.45",
            description = "Basque chocolate cheesecake with cream cheese and dark chunks.",
            volume = "170 g",
            category = MenuCategory.DESSERTS,
            imageResId = R.drawable.cheesecake
        ),
        MenuItem(
            name = "Coconut Éclair",
            price = "$2.00",
            description = "Light choux pastry filled with delicate coconut cream and topped with a coconut glaze.",
            volume = "90 g",
            category = MenuCategory.DESSERTS,
            imageResId = R.drawable.coconut_eclair
        ),
        MenuItem(
            name = "Brownie",
            price = "$1.80",
            description = "Moist and rich chocolate brownie with a fudgy center and a crisp top.",
            volume = "100 g",
            category = MenuCategory.DESSERTS,
            imageResId = R.drawable.brownie
        ),
        MenuItem(
            name = "Mini Lemon Cake",
            price = "$2.40",
            description = "Lemon cake with a white chocolate", // Описание обрезано на изображении
            volume = "85 g",
            category = MenuCategory.DESSERTS,
            imageResId = R.drawable.lemon_cake
        ),

        // Snacks
        MenuItem(
            name = "Pastrami Melt",
            price = "$6.40",
            description = "Multigrain bread with spiced pastrami, truffle mayo, tomato, melted cheese, and herb butter.",
            volume = "310 g",
            category = MenuCategory.SNACKS,
            imageResId = R.drawable.pastrami_melt
        ),
        MenuItem(
            name = "Tofu Fresh Roll",
            price = "$2.70",
            description = "Wheat tortilla with lettuce, cherry tomatoes, smoked tofu, and harissa sauce.",
            volume = "270 g",
            category = MenuCategory.SNACKS,
            imageResId = R.drawable.tofu_fresh_roll
        ),
        MenuItem(
            name = "Roast Beef Sandwich",
            price = "$4.90",
            description = "Tortilla with iceberg lettuce, boiled beef, cherry tomatoes, pickles, Caesar and sweet & sour sauces.",
            volume = "280 g",
            category = MenuCategory.SNACKS,
            imageResId = R.drawable.beef_sandwich
        )
    )
}
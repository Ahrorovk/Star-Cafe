package com.example.starcafe.presentation.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.border // <-- Добавляем импорт для Modifier.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.starcafe.components.MenuItemCard
import com.example.starcafe.data.model.MenuCategory

@Composable
fun MenuScreen(viewModel: MenuViewModel) {
    val state by viewModel.state.collectAsState()

    val SelectedBorderColor = Color(0xFF0FA2F7)
    val SelectedTransparentBlue = Color(0xFF0FA2F7).copy(alpha = 0.25f)
    val DefaultBackgroundColor = Color(0xFF2C2C2C)

    Column {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(MenuCategory.entries) { category ->
                val selected = state.selectedCategory == category

                val buttonModifier = if (selected) {
                    Modifier
                        .border(
                            width = 2.dp,
                            color = SelectedBorderColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                } else {
                    Modifier
                }

                Button(
                    onClick = { viewModel.onEvent(MenuIntent.SelectCategory(category)) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selected) SelectedTransparentBlue else DefaultBackgroundColor,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier = buttonModifier
                ) {
                    Row(verticalAlignment = CenterVertically) {
                        Icon(
                            painter = painterResource(id = category.iconResId),
                            contentDescription = category.name,
                            modifier = Modifier.size(24.dp),
                            tint = Color(0xFFF68B0D)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(category.name, color = Color.White)
                    }
                }
            }
        }

        val filtered = state.manuItem.filter { it.category == state.selectedCategory }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filtered) { item ->
                MenuItemCard(item)
            }
        }
    }
}
package com.example.starcafe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoSection(title: String, description: String, note: String? = null) {
    Column {
        Text(
            text = "⭐ $title",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            fontSize = 18.sp,
            color = Color(0xFFDADADA),
            lineHeight = 22.sp
        )
        note?.let {
            Text(
                text = it,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFDADADA)
            )
        }
    }
}

@Composable
fun InfoLevelSection(title: String, description: String, levels: List<String>, footer: String) {
    Column {
        Text(
            text = "⭐ $title",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            fontSize = 18.sp,
            color = Color(0xFFDADADA),
            lineHeight = 22.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        levels.forEach { level ->
            Text(
                text = level,
                fontSize = 16.sp,
                color = Color(0xFFDADADA),
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = footer,
            fontSize = 18.sp,
            color = Color(0xFFDADADA)
        )
    }
}
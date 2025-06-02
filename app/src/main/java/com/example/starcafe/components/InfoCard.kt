package com.example.starcafe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoCard(
    title: String,
    icon: @Composable () -> Unit,
    primaryText: String,
    secondaryText: String? = null,
    primaryColor: Color = Color.White
) {
    Column(
        modifier = Modifier
            .size(width = 182.dp, height = 142.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1C1C1E))
            .padding(vertical = 16.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Divider(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(1.dp)
                .fillMaxWidth(),
            color = Color.DarkGray
        )
        Row (verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.padding(end = 6.dp)) {
                icon()
            }
            Text(
                text = primaryText,
                color = primaryColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (!secondaryText.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = secondaryText, color = Color.White, fontSize = 14.sp)
        }
    }
}
package com.example.starcafe.presentation.contact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starcafe.components.LocationInfoItem
import com.example.starcafe.data.model.ContactItem

@Composable
fun ContactLocationScreen() {
        val starCafeLocations: List<ContactItem> = listOf(
        ContactItem(
            name = "Cape Town CBD",
            address = "18 Loop Street, Cape Town, 8001",
            phone = "+27 21 456 7890",
            openingHours = "Mon-Fri: 07:30–19:00\nSat-Sun: 09:00–17:00"
        ),
        ContactItem(
            name = "Johannesburg Sandton",
            address = "75 Rivonia Road, Sandton, Johannesburg, 2196",
            phone = "+27 11 234 5678",
            openingHours = "Mon-Sun: 07:00–20:00"
        ),
        ContactItem(
            name = "Durban Central",
            address = "102 Florida Road, Durban, 4001",
            phone = "+27 31 789 4561",
            openingHours = "Mon-Fri: 07:30–19:00\nSat-Sun: 09:00–17:00"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                "Contact & Locations",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            starCafeLocations.forEach { item ->
                LocationInfoItem(
                    contactItem = item
                )
            }
        }
    }
}
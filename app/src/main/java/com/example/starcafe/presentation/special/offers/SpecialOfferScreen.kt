package com.example.starcafe.presentation.special.offers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starcafe.R
import com.example.starcafe.components.SpecialOfferComponent
import com.example.starcafe.data.model.SpecialOfferItem

@Composable
fun SpecialOfferScreen() {
    val specialOfferData = listOf(
        SpecialOfferItem("Buy any coffee and get a Coconut Éclair absolutely free!", "Valid Until: June 30, 2025", R.drawable.coconut_eclair_offer),
        SpecialOfferItem("Matcha Latte + Brownie Combo — \$5.00", "Valid: Weekdays only, \nfrom 12:00 to 17:00.", R.drawable.latte_offer),
        SpecialOfferItem("Order a Pastrami Melt and receive double stars (up to 14%) for this purchase", "Valid: Until July 15, 2025", R.drawable.patrami_offer)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        specialOfferData.forEach { item ->
            SpecialOfferComponent(
                specialOfferItem = item
            )
        }
    }
}
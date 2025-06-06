package com.example.starcafe.presentation.special.offers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.starcafe.R
import com.example.starcafe.components.SpecialOfferComponent
import com.example.starcafe.data.model.SpecialOfferItem

@Composable
fun SpecialOfferScreen() {
    val specialOfferData = listOf(
        SpecialOfferItem(
            title = buildAnnotatedString {
                append("Buy any coffee and get a ")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFFFFA726),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Coconut Éclair absolutely free!")
                }
            },
            date = "Valid Until: June 30, 2025",
            imageResId = R.drawable.coconut_eclair_offer
        ),
        SpecialOfferItem(
            title = buildAnnotatedString {
                append("Matcha Latte + Brownie Combo — ")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFFFFA726),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("$5.00")
                }
            },
            date = "Valid: Weekdays only, \nfrom 12:00 to 17:00.",
            imageResId = R.drawable.latte_offer
        ),
        SpecialOfferItem(
            title = buildAnnotatedString {
                append("Order a Pastrami Melt and receive ")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFFFFA726),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("double stars")
                }
                append(" (up to 14%) for this purchase")
            },
            date = "Valid: Until July 15, 2025",
            imageResId = R.drawable.patrami_offer
        )
    )


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(specialOfferData) { item ->
            SpecialOfferComponent(
                specialOfferItem = item
            )
        }
    }
}
package com.example.starcafe.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TransactionHistoryScreen(
    state: TransactionState,
    onIntent: (TransactionIntent) -> Unit
) {

    LaunchedEffect(Unit) {
        onIntent(TransactionIntent.LoadTransactions)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text("Transaction History", color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.padding(vertical = 8.dp)) {
            items(state.transactions) { transaction ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(
                            text = transaction.description,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                        Text(
                            text = SimpleDateFormat(
                                "MMM dd, yyyy, HH:mm",
                                Locale.getDefault()
                            ).format(
                                Date(
                                    transaction.timestamp
                                )
                            ),
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "",
                            tint = if (transaction.stars > 0) Color.Green else Color.Red
                        )
                        Text(
                            text = (if (transaction.stars > 0) "+${transaction.stars}" else "${transaction.stars}"),
                            color = if (transaction.stars > 0) Color.Green else Color.Red,
                            fontSize = 16.sp,
                        )
                    }
                }
                Divider(color = Color.DarkGray, thickness = 1.dp)
            }
        }
    }
}

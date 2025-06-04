package com.example.starcafe.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        Text("Transaction History", color = Color.White, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        state.transactions.forEach { transaction ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    text = transaction.description,
                    color = Color.White,
                    fontSize = 16.sp
                )
                Text(
                    text = SimpleDateFormat("MMM dd, yyyy, HH:mm", Locale.getDefault()).format(
                        Date(
                            transaction.timestamp
                        )
                    ),
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = (if (transaction.stars > 0) "+${transaction.stars}" else "${transaction.stars}"),
                    color = if (transaction.stars > 0) Color.Green else Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.End)
                )
                Divider(color = Color.DarkGray, thickness = 1.dp)
            }
        }
    }
}

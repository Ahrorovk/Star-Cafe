package com.example.starcafe.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.starcafe.R
import com.example.starcafe.components.InfoCard
import com.example.starcafe.components.StarDialog
import com.example.starcafe.core.Routes
import com.example.starcafe.data.local.dataStore.DataStoreManager
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    val qrCodeBitmap = remember(state.qrCode) {
        val size = 524

        if (state.qrCode.isNullOrBlank()) return@remember null

        val bits = QRCodeWriter().encode(state.qrCode, BarcodeFormat.QR_CODE, size, size)
        val bitmap = android.graphics.Bitmap.createBitmap(size, size, android.graphics.Bitmap.Config.RGB_565)
        for (x in 0 until size) {
            for (y in 0 until size) {
                bitmap.setPixel(x, y, if (bits.get(x, y)) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
            }
        }
        bitmap
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp)
                        .padding(top = 16.dp)
                        .background(Color(0xFF151617)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Show this code to earn stars",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (qrCodeBitmap != null) {
                        Image(
                            bitmap = qrCodeBitmap.asImageBitmap(),
                            contentDescription = "QR Code",
                            modifier = Modifier.size(220.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${state.randomNumber}",
                        color = Color.White,
                        letterSpacing = 2.sp
                    )
                }


                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    InfoCard(
                        title = "Star Balance",
                        icon = { Text("⭐", fontSize = 20.sp, color = Color(0xFFF68B0D)) },
                        primaryText = "${state.starBalance}",
                        primaryColor = Color.White,
                    )

                    // Определяем иконку и кэшбэк в зависимости от уровня
                    val loyaltyLevel = state.loyaltyLevel
                    val (cashbackText, iconRes) = when (loyaltyLevel) {
                        "Gold" -> "7% cashback" to R.drawable.level_gold
                        "Silver" -> "5% cashback" to R.drawable.loyalty_level
                        "Bronze" -> "3% cashback" to R.drawable.level_bronze
                        else -> "0% cashback" to R.drawable.level_starter
                    }

                    InfoCard(
                        title = "Loyalty Level",
                        icon = {
                            Icon(
                                painter = painterResource(id = iconRes),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        },
                        primaryText = loyaltyLevel,
                        secondaryText = cashbackText,
                        primaryColor = Color.White,
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { viewModel.onEvent(HomeIntent.OnAddStarsClicked) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00AEEF)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("ADD STARS", color = Color.White)
                }
            }
        }
            if (state.showDialog) {
                StarDialog(
                    onDismiss = { viewModel.onEvent(HomeIntent.OnDismissDialog) },
                    onConfirm = { viewModel.onEvent(HomeIntent.OnConfirmAddStars)
                        viewModel.onEvent(HomeIntent.OnInputStarsChanged(""))},
                    inputStars = state.inputStars,
                    onStarsChanged = { viewModel.onEvent(HomeIntent.OnInputStarsChanged(it)) }
                )
            }
    }
}
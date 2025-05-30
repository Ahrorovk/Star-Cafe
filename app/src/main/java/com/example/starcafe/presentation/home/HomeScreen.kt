package com.example.starcafe.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlin.random.Random

@Composable
fun HomeScreen(navController: NavController) {
    val qrCode = remember { Random.nextLong(1000000000L, 9999999999L).toString() }

    val qrCodeBitmap = remember(qrCode) {
        val size = 512
        val bits = QRCodeWriter().encode(qrCode, BarcodeFormat.QR_CODE, size, size)
        val bitmap = android.graphics.Bitmap.createBitmap(size, size, android.graphics.Bitmap.Config.RGB_565)
        for (x in 0 until size) {
            for (y in 0 until size) {
                bitmap.setPixel(x, y, if (bits.get(x, y)) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
            }
        }
        bitmap
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Show this code to earn stars",
            color = Color.White,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            bitmap = qrCodeBitmap.asImageBitmap(),
            contentDescription = "QR Code",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Star Balance", color = Color.White, fontSize = 16.sp)
                Text("⭐️ 137", color = Color.White, fontSize = 20.sp)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Loyalty Level", color = Color.White, fontSize = 16.sp)
                Text("💎 Silver", color = Color.White, fontSize = 20.sp)
                Text("5% cashback", color = Color.White, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* TODO: Implement Add Stars functionality */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00AEEF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ADD STARS", color = Color.White)
        }
    }
}
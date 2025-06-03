package com.example.starcafe.presentation.instruction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starcafe.components.InfoLevelSection
import com.example.starcafe.components.InfoSection

@Composable
fun InstructionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "How it works",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoSection(
            title = "Earn Stars",
            description = """
                Show your personal QR code or loyalty code to the barista every time you make a purchase at STAR CAFE.

                You'll receive stars as cashback based on your current loyalty level (up to 7% of your total purchase).
            """.trimIndent(),
            note = "Note: After the barista informs you of the earned stars, tap the “Add Stars” button on the home screen to manually add them to your balance."
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoSection(
            title = "Redeem Stars",
            description = """
                Exchange your accumulated stars for delicious rewards such as drinks and desserts.

                Simply go to the Rewards section, choose your favorite item, and tap Redeem. Then show the confirmation screen to the barista to claim your reward.
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoLevelSection(
            title = "Loyalty Levels",
            description = """
                Your level depends on the total amount you've spent at STAR CAFE. The more you spend, the higher your level and cashback rate:
            """.trimIndent(),
            levels = listOf(
                "• Bronze: up to 1000 spent units – cashback 3%",
                "• Silver: 1001 to 5000 spent units – cashback 5%",
                "• Gold: over 5000 spent units – cashback 7%"
            ),
            footer = "Enjoy your rewards and keep visiting STAR CAFE to maximize your benefits!"
        )
    }
}



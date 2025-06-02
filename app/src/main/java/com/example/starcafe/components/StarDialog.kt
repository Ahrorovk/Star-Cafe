package com.example.starcafe.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun StarDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    inputStars: String,
    onStarsChanged: (String) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .width(369.dp)
                .height(288.dp)
                .background(color = Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
            ) {
                Text(
                    text = "Add your stars",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Number of stars received",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = inputStars,
                    onValueChange = { onStarsChanged(it) },
                    label = { Text("Enter the number") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFF2C2C2C),
                        focusedContainerColor = Color(0xFF2C2C2C),
                        focusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    OutlinedButton(
                        onClick = onDismiss,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color.Yellow),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = Color.Black,
                            contentColor = Color.Yellow
                        )
                    ) {
                        Text("Cancel", color = Color.Yellow)
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    Button(
                        onClick = { onConfirm(inputStars) },
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Confirm", color = Color.Black)
                    }
                }
            }
        }
    }
}

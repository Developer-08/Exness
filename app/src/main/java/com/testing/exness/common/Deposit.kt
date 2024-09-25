package com.testing.exness.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DepositScreen(
    onDismiss:()-> Unit
) {
    BackHandler {
        onDismiss()
    }

    var depositAmount by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFFAFBFA))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable(
                            onClick = { onDismiss() },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
                Text(text = " Deposit", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(50.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "Enter deposit amount", fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(25.dp))

            Column(
                modifier = Modifier.padding(8.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = depositAmount.toString(),
                        onValueChange = { newText ->
                            // Parse the input to an integer, and ignore any non-numeric input
                            depositAmount = newText.toIntOrNull() ?: depositAmount
                        },
                        textStyle = TextStyle(color = Color.Black)
                    )
                    Text(text = "USD", fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(4.dp))
                HorizontalDivider()

                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "0.00 – 10,000,000,000.00 USD",
                        fontSize = 12.sp
                    )
                }
            }
        }

        Box(modifier = Modifier.padding(8.dp)) {
            Button(
                onClick = { /* Continue action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(Color(0xFFFFD54F))
            ) {
                Text(
                    text = "Continue", fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}


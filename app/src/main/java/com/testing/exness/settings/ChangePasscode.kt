package com.testing.exness.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testing.exness.R

@Composable
fun ChangePasscodeScreen(onDismiss: () -> Unit) {

    BackHandler { onDismiss() }

    var passcode by remember { mutableStateOf("") }
    var isReentering by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier, verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "",
                    modifier = Modifier
                        .clickable(
                            onClick = { onDismiss() },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ))
                Text(
                    text = "  Change passcode", fontSize = 24.sp, fontWeight = FontWeight.SemiBold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isReentering) "Re-enter your passcode"
                    else "Enter your existing passcode",
                    fontSize = 14.sp
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Passcode dots
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(6) { index ->
                PasscodeDot(isFilled = index < passcode.length)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Keypad
        Column(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("1", "2", "3", "4", "5", "6", "7", "8", "9").chunked(3).forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(54.dp)
                ) {
                    row.forEach { digit ->
                        Button(
                            onClick = {
                                if (passcode.length < 6) passcode += digit
                                if (passcode.length == 6) {
                                    // When passcode is complete, clear it and update the text
                                    passcode = ""
                                    isReentering = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            modifier = Modifier.size(70.dp)
                        ) {
                            Text(
                                text = digit, fontSize = 34.sp, color = Color.Black
                            )
                        }
                    }
                }
            }

            // Last row: "0" and Backspace button
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Empty space below 7 and 9
                Spacer(modifier = Modifier.size(60.dp))

                // "0" button below "8"
                Button(
                    onClick = {
                        if (passcode.length < 6) passcode += "0"
                        if (passcode.length == 6) {
                            // When passcode is complete, clear it and update the text
                            passcode = ""
                            isReentering = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier.size(70.dp)
                ) {
                    Text(
                        text = "0", fontSize = 34.sp, color = Color.Black
                    )
                }

                // Backspace button below "9"
                if (passcode.isNotEmpty()) {
                    Button(
                        onClick = {
                            passcode = passcode.dropLast(1)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier.size(70.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back Arrow",
                            tint = Color.Black
                        )
                    }
                } else {
                    // Empty space for Backspace button
                    Spacer(modifier = Modifier.size(40.dp))
                }
            }
        }
    }
}


@Composable
fun PasscodeDot(isFilled: Boolean) {
    val darkYellow = Color(0xFFFFD700)
    Box(
        modifier = Modifier.size(18.dp), contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .border(
                    2.dp,
                    if (isFilled) darkYellow else Color.LightGray,
                    shape = RoundedCornerShape(50)
                )
                .background(
                    if (isFilled) darkYellow else Color.White, shape = RoundedCornerShape(50)
                )
        )
    }
}

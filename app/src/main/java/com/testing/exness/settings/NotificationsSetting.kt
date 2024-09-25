package com.testing.exness.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NotificationSetting(onDismiss: () -> Unit) {
    BackHandler { onDismiss() }
    Column(
        modifier = Modifier
            .background(color = Color(0xFFFAFBFA))
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
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
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                )
                Text(text = "   Notifications", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
            Operations()
        }
    }
}


@Composable
fun Operations() {
    Spacer(modifier = Modifier.height(30.dp))

    var signals by remember { mutableStateOf(false) }
    var news by remember { mutableStateOf(false) }
    var calendar by remember { mutableStateOf(false) }
    var movements by remember { mutableStateOf(false) }

    var tradingOperaton by remember { mutableStateOf(false) }
    var financialOperation by remember { mutableStateOf(false) }


    Column {
        Text(
            text = "    INFORMATION", color = Color.Gray, fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(16.dp)
                    .height(200.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = { },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Instruments", fontSize = 14.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Favourites  ", fontSize = 14.sp)
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "",
                            modifier = Modifier
                                .size(28.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = { signals = true },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Trading signals", fontSize = 14.sp
                    )
                    if (signals) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = { news = true },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "News", fontSize = 14.sp
                    )
                    if (news) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = { calendar = true },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Economic calendar", fontSize = 14.sp
                    )
                    if (calendar) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = { movements = true },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Price movements", fontSize = 14.sp
                    )
                    if (movements) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                        )
                    }
                }

            }
        }
    }
    Spacer(modifier = Modifier.height(30.dp))

    Column {
        Text(
            text = "    OPERATIONS", color = Color.Gray, fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(16.dp)
                    .height(60.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = { tradingOperaton = true },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Trading", fontSize = 14.sp
                    )
                    if (tradingOperaton) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = { financialOperation = true },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Financial", fontSize = 14.sp
                    )
                    if (financialOperation) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                        )
                    }
                }
            }
        }
    }
}

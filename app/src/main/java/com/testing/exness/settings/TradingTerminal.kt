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
fun TradingTerminal(
    onDismiss:()-> Unit
) {
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
                Text(text = "   Trading Terminal", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
            Terminals()
        }
    }
}


@Composable
fun Terminals() {
    Spacer(modifier = Modifier.height(30.dp))

    var selectedTerminal by remember { mutableStateOf("Exness") }

    Column {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(16.dp)
                    .height(150.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = { selectedTerminal = "Exness" },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Exness", fontSize = 14.sp
                    )
                    if (selectedTerminal == "Exness") {
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
                            onClick = { selectedTerminal = "TradingView" },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "TradingView", fontSize = 14.sp
                    )
                    if (selectedTerminal == "TradingView") {
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
                            onClick = { selectedTerminal = "Built-in MetaTrader 5" },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Built-in MetaTrader 5", fontSize = 14.sp
                    )
                    if (selectedTerminal == "Built-in MetaTrader 5") {
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
                            onClick = { selectedTerminal = "MetaTrader 5" },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "MetaTrader 5 app", fontSize = 14.sp
                    )
                    if (selectedTerminal == "MetaTrader 5") {
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

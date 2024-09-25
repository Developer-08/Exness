package com.testing.exness.mainscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testing.exness.R



@Composable
fun MarketScreen(
    navigateToDetails: () -> Unit
) {
    var backPressedOnce by remember { mutableStateOf(false) }
    val context = LocalContext.current

    BackHandler {
        if (backPressedOnce) {
            // Exit the app on second back press
            (context as? Activity)?.finish()
        } else {
            // Show toast message on first back press
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressedOnce = true
        }
    }

    // Reset the back press state after 2 seconds
    if (backPressedOnce) {
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(2000) // Delay for 2 seconds
            backPressedOnce = false
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFBFA))
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = " Market", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "#181564870", fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                Icons.Filled.KeyboardArrowDown, contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable(
                                        onClick = { },
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    )
                            )
                        }
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_format_list_bulleted_24),
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                onClick = { navigateToDetails() },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {
                    // Demo Label
                    Surface(
                        color = Color(0xFFE6F9E6), // Light green background
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = "Demo",
                            color = Color(0xFF3CB371),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }

                    // MT5 Label
                    Surface(
                        color = Color(0xFFF0F0F0), // Light gray background color
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = "MT5",
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }

                    // Standard Label
                    Surface(
                        color = Color(0xFFF0F0F0), // Light gray background color
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "Standard",
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "23,482.69 USD", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                // TOP MOVERS
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "TOP MOVERS", color = Color.Gray, fontSize = 14.sp,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "Show more", color = Color.Blue, fontSize = 14.sp,

                        )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // TRADING SIGNALS
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "TRADING SIGNALS", color = Color.Gray, fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "Show more", color = Color.Blue, fontSize = 14.sp,

                            )
                    }

                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .horizontalScroll(rememberScrollState())
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.signals),
                            contentDescription = "",
                            modifier = Modifier
                                .size(230.dp)
                                .clickable(
                                    onClick = { },
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.signals),
                            contentDescription = "",
                            modifier = Modifier
                                .size(230.dp)
                                .clickable(
                                    onClick = { },
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // UPCOMING EVENTS
                Column(
                    modifier = Modifier
//                    .border(2.dp, color = Color.Red)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "UPCOMING EVENTS", color = Color.Gray, fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "Show more", color = Color.Blue, fontSize = 14.sp,

                            )
                    }

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        EventCard()
                    }
                }
            }
        }
    }
}

@Composable
fun Events(
    eventIcon: Painter,
    eventName: String,
    eventDescription: String,
    eventTime: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .width(330.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = eventIcon,
                    contentDescription = eventName,
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = eventName, fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = eventDescription,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(10.dp))

                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(14.dp)
                        .background(Color.Red)
                )
                Spacer(modifier = Modifier.width(1.dp))
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(14.dp)
                        .background(Color.Yellow)
                )
                Spacer(modifier = Modifier.width(1.dp))
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(14.dp)
                        .background(Color.LightGray)
                )

                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = eventTime,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun EventCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Events(
            eventIcon = painterResource(id = R.drawable.asset),
            eventName = "Gross Domestic Product (YoY)",
            eventDescription = "SAR",
            eventTime = "11:30 AM",
        )
    }

    Spacer(modifier = Modifier.height(2.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Events(
            eventIcon = painterResource(id = R.drawable.php),
            eventName = "Osmena Day",
            eventDescription = "PHP",
            eventTime = "09:30 AM",
        )
    }

    Spacer(modifier = Modifier.height(2.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Events(
            eventIcon = painterResource(id = R.drawable.jpy),
            eventName = "Gross Domestic Product (QoQ)",
            eventDescription = "JPY",
            eventTime = "05:20 AM",
        )
    }
}
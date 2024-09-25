package com.testing.exness.mainscreen

import android.app.Activity
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testing.exness.R
import android.net.Uri



@Composable
fun ProfileScreen(
    navigateToSettings: () -> Unit,
    navigateToHelpCenter: () -> Unit,
    onLogout: () -> Unit
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
            .background(color = Color(0xFFFAFBFA))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Profile", fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                Icons.Filled.Settings, contentDescription = "",
                modifier = Modifier
                    .size(26.dp)
                    .clickable(
                        onClick = { navigateToSettings() },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
            )
        }

        ///////////////////////////////////////////////////////////////////////////////////////
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column {
                Text(
                    text = "  BENEFITS", color = Color.Gray, fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.height(6.dp))
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
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
                            Column(
                                modifier = Modifier
                                    .padding(top = 8.dp, end = 8.dp)
                                    .height(120.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row {
                                        Icon(
                                            painter = painterResource(id = R.drawable.swap_24),
                                            contentDescription = "Arrow",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .padding(end = 10.dp)
                                                .size(24.dp)
                                                .clickable { }
                                        )
                                        Text(
                                            text = "Swap-free", fontSize = 14.sp
                                        )
                                    }

                                    Surface(
                                        color = Color(0xFFE6F9E6), // Light green background
                                        shape = RoundedCornerShape(50.dp)
                                    ) {
                                        Text(
                                            text = "Extended",
                                            color = Color(0xFF3CB371),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(
                                                horizontal = 8.dp,
                                                vertical = 2.dp
                                            )
                                        )
                                    }
                                }
                                Row {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_shield_24),
                                        contentDescription = "Arrow",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .padding(end = 10.dp)
                                            .size(24.dp)
                                            .clickable { }
                                    )
                                    Text(
                                        text = "Negative Balance Protection", fontSize = 14.sp
                                    )
                                }
                                Row {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_dataset_24),
                                        contentDescription = "Arrow",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .padding(end = 10.dp)
                                            .size(24.dp)
                                            .clickable { }
                                    )
                                    Text(
                                        text = "Virtual Private Server", fontSize = 14.sp
                                    )
                                }

                            }

                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .height(50.dp),
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Surface(
                                    color = Color(0xFFE6F9E6), // Light green background
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(
                                        text = "Extended",
                                        color = Color(0xFF3CB371),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(
                                            horizontal = 4.dp,
                                            vertical = 2.dp
                                        )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "  CRYPTO WALLET", color = Color.Gray, fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(6.dp))
            Card(
                modifier = Modifier.clickable { },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_wallet_24),
                            contentDescription = "Back Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(28.dp)
                                .padding(end = 10.dp)
                                .clickable { }
                        )
                        Column {
                            Text(
                                text = "Balance", color = Color.Gray, fontSize = 14.sp,
                                lineHeight = 14.sp
                            )
                            Text(
                                text = "0.00 USD", fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            // REFERRAL PROGRAM
            Text(
                text = "  REFERRAL PROGRAM", color = Color.Gray, fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(6.dp))
            Card(
                modifier = Modifier.clickable { },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.clients_24),
                            contentDescription = "Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(28.dp)
                                .clickable { }
                        )
                        Text(
                            text = "Earn a stable income by introducing clients to Exness",
                            fontSize = 16.sp, fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            // SOCIAL TRADING
            Text(
                text = "  SOCIAL TRADING", color = Color.Gray, fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(6.dp))
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    val url = "https://my.exness.com/pa/socialtrading/strategies"
                    Row(
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                }
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_badge_24),
                            contentDescription = "Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable(
                                    onClick = {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                        context.startActivity(intent)
                                    }
                                )
                        )
                        Column(
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(
                                text = "For professional traders", fontSize = 14.sp,
                                lineHeight = 16.sp
                            )
                            Text(
                                text = "Boost your income by sharing your trading strategies",
                                color = Color.Gray, fontSize = 12.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    val URL = "https://social-trading.exness.com"
                    Row(
                        modifier = Modifier
                            .clickable(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(URL))
                                context.startActivity(intent)
                            }
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.clients_24),
                            contentDescription = "Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable(
                                    onClick = {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(URL))
                                        context.startActivity(intent)
                                    }
                                )
                        )
                        Column(
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(
                                text = "For investors", fontSize = 14.sp,
                                lineHeight = 16.sp
                            )
                            Text(
                                text = "Copy successful strategies of other traders",
                                fontSize = 12.sp, color = Color.Gray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // SUPPORT
            Text(
                text = "  SUPPORT", color = Color.Gray, fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(6.dp))
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    val url = "https://my.exness.com/pa/support_hub/help_center"
                    Row(
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                }
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.help_24),
                            contentDescription = "Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { }
                        )
                        Column(
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(
                                text = "Help Center", fontSize = 14.sp,
                                lineHeight = 16.sp
                            )
                            Text(
                                text = "Find answers to your questions",
                                color = Color.Gray, fontSize = 12.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ticket_24),
                            contentDescription = "Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { }
                        )
                        Column(
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(
                                text = "Open a ticket", fontSize = 14.sp
                            )
                            Text(
                                text = "Fill the request form and we'll get back to you",
                                fontSize = 12.sp, color = Color.Gray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.live_chat_24),
                            contentDescription = "Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { }
                        )
                        Column(
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(
                                text = "LiveChat", fontSize = 14.sp,
                                lineHeight = 14.sp
                            )
                            Text(
                                text = "Feel free to contact our customer support",
                                fontSize = 12.sp, color = Color.Gray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.lightbulb_24),
                            contentDescription = "Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable { }
                        )
                        Column(
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(
                                text = "Suggest a feature", fontSize = 14.sp,
                                lineHeight = 16.sp
                            )
                            Text(
                                text = "Help us became better",
                                fontSize = 12.sp, color = Color.Gray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.document_24),
                            contentDescription = "Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { }
                        )
                        Column(
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(
                                text = "Legal Documents", fontSize = 14.sp,
                                lineHeight = 16.sp
                            )
                            Text(
                                text = "Exness(SC)Ltd",
                                fontSize = 12.sp, color = Color.Gray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .clickable {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=${context.packageName}")
                                )
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                context.startActivity(intent)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_thumb_up_off_alt_24),
                            contentDescription = "Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable {
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("market://details?id=${context.packageName}")
                                    )
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    context.startActivity(intent)
                                }
                        )
                        Column(
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(
                                text = "Rate the app", fontSize = 14.sp,
                                lineHeight = 16.sp
                            )
                            Text(
                                text = "Please rate us in Google Play",
                                fontSize = 12.sp, color = Color.Gray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Card(
                modifier = Modifier.clickable { },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .clickable(
                            onClick = { onLogout() },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .clickable(
                                onClick = { onLogout() },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                            contentDescription = "Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable(
                                    onClick = { onLogout() },
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                        )
                        Column {
                            Text(
                                text = "  Log Out", fontSize = 14.sp,
                                lineHeight = 16.sp
                            )
                            Text(
                                text = "  exness@gmail.com", fontSize = 12.sp, color = Color.Gray
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}


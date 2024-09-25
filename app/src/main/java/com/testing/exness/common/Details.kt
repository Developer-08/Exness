package com.testing.exness.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testing.exness.R

@Composable
fun DetailScreen(
    onDismiss: () -> Unit,
    navigateToDepositScreen: () -> Unit,
    navigateToWithdrawScreen: () -> Unit,
    navigateToNameAccount: () -> Unit
) {
    BackHandler {
        onDismiss()
    }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFBFA))
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onDismiss() }) {
                Icon(Icons.Filled.Close, contentDescription = "")
            }

            Text(text = " #181564870", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Custom Tab Row
        CustomTabRow(
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { index -> selectedTabIndex = index }
        )
        Spacer(modifier = Modifier.height(18.dp))

        // Conditionally display UI based on selected tab
        when (selectedTabIndex) {
            0 -> FundsUi(navigateToDepositScreen, navigateToWithdrawScreen)
            1 -> SettingsUi(navigateToNameAccount = navigateToNameAccount)
        }
    }
}


// Funds/Settings
@Composable
fun CustomTabRow(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabTitles = listOf("Funds", "Settings")

    TabRow(
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .height(2.dp),
                color = Color.Black
            )
        },
        containerColor = Color(0xFFFAFBFA)
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = title,
                        color = if (selectedTabIndex == index) Color.Black else Color.Gray,
                        fontSize = 16.sp
                    )
                }
            )
        }
    }
}


@Composable
fun FundsUi(onDeposit: () -> Unit, onWithdraw: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                        .padding(8.dp)
                        .height(250.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Balance", fontSize = 14.sp
                    )
                    Text(
                        text = "Equity", fontSize = 14.sp
                    )
                    Text(
                        text = "Total P/L", fontSize = 14.sp
                    )
                    Text(
                        text = "Margin", fontSize = 14.sp
                    )
                    Text(
                        text = "Free margin", fontSize = 14.sp
                    )
                    Text(
                        text = "Margin level", fontSize = 14.sp
                    )
                    Text(
                        text = "Leverage", fontSize = 14.sp
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(250.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "23,842.69 USD", fontSize = 14.sp
                    )
                    Text(
                        text = "23,842.69 USD", fontSize = 14.sp
                    )
                    Text(
                        text = "0.00 USD", fontSize = 14.sp
                    )
                    Text(
                        text = "0.00 USD", fontSize = 14.sp
                    )
                    Text(
                        text = "23,842.69 USD", fontSize = 14.sp
                    )
                    Text(
                        text = "-", fontSize = 14.sp
                    )
                    Text(
                        text = "1:100", fontSize = 14.sp
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    DepositWithdraw(
        onDeposit = onDeposit,
        onWithdraw = onWithdraw
    )
}

@Composable
fun SettingsUi(navigateToNameAccount: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                        .padding(8.dp)
                        .height(50.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Type", fontSize = 14.sp
                    )
                    Text(
                        text = "Number", fontSize = 14.sp
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(50.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
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

                    Text(
                        text = "#181564870", fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .clickable(
                        onClick = { navigateToNameAccount() },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "  Name", fontSize = 14.sp
                )
                Row {
                    Text(
                        text = "Add a custom name(optional)  ", fontSize = 14.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = "Back Arrow",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(22.dp)
                            .clickable(
                                onClick = { navigateToNameAccount() },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(40.dp))

    // Standard Account
    Column {
        Text(
            text = "   STANDARD ACCOUNT", color = Color.Gray, fontSize = 14.sp,
            fontFamily = FontFamily.Monospace
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
                            .padding(8.dp)
                            .height(50.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "No commission", fontSize = 14.sp
                        )
                        Text(
                            text = "Minimum spread", fontSize = 14.sp
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(50.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "", fontSize = 14.sp
                        )
                        Text(
                            text = "0.2", fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
//                    .border(1.dp, color = Color.Red)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "  Maximum leverage", fontSize = 14.sp
                    )
                    Row {

                        Text(
                            text = "1:100  ", fontSize = 14.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                            contentDescription = "Back Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(22.dp)
                                .clickable { }
                        )
                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(40.dp))

    // Trading Platform
    Column {
        Text(
            text = "  TRADING PLATFORM - META TRADER 5",
            color = Color.Gray,
            fontSize = 14.sp,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(5.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
//            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Column(
                modifier = Modifier
                    .height(150.dp)
                    .background(color = Color.White)
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),//.border(1.dp, color = Color.Green),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Login", fontSize = 14.sp
                    )
                    Row(
                        modifier = Modifier, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "181564870   ", fontSize = 14.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_content_copy_24),
                            contentDescription = "Back Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(22.dp)
                                .clickable { }
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Server", fontSize = 14.sp
                    )
                    Row(
                        modifier = Modifier, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Exness-MT5Trial7  ", fontSize = 14.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_content_copy_24),
                            contentDescription = "Back Arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(22.dp)
                                .clickable { }
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Change trading password", fontSize = 14.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = "Back Arrow",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(22.dp)
                            .clickable { }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Trading log", fontSize = 14.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = "Back Arrow",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(22.dp)
                            .clickable { }
                    )
                }
            }
        }
    }
}


@Composable
fun DepositWithdraw(onDeposit: () -> Unit, onWithdraw: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
//        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .width(130.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .background(
                            color = Color(0xFFF0F0F0), shape = RoundedCornerShape(50)
                        ), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_circle_down_24),
                        contentDescription = "Arrow",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                onClick = { onDeposit() },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Deposit", fontSize = 12.sp)
            }

            Column(
                modifier = Modifier
                    .padding(end = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .background(
                            color = Color(0xFFF0F0F0), shape = RoundedCornerShape(50)
                        ), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_circle_up_24),
                        contentDescription = "Arrow",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                onClick = { onWithdraw() },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Withdraw", fontSize = 12.sp)
            }
        }
    }
}

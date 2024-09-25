package com.testing.exness.mainscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testing.exness.R
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.testing.exness.BinanceWebSocket
import androidx.lifecycle.viewmodel.compose.viewModel
import com.testing.exness.CryptoViewModel


@Composable
fun TradeScreen(
    navigateToAlert: () -> Unit,
    navigateToDetails: () -> Unit
) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }
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
//            .background(color = Color.White)
            .background(color = Color(0xFFFAFBFA))
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
//                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = " Trade", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                Icon(
                    painter = painterResource(id = R.drawable.baseline_alarm_24),
                    contentDescription = "Arrow",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            onClick = { navigateToAlert() },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
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
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ScrollableTabRow(
                    selectedTabIndex = selectedTabIndex,
                    onTabSelected = { index -> selectedTabIndex = index }
                )
                HorizontalDivider()
                Spacer(modifier = Modifier.height(10.dp))
                EditFilterRow()

                Spacer(modifier = Modifier.height(18.dp))

                // Conditionally display UI based on selected tab
                when (selectedTabIndex) {
                    0 -> BinanceWebSocket()
                    1 -> {}
                    else -> {}
                }

            }
        }
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////





//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun EditFilterRow() {
    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {

            Icon(
                painter = painterResource(id = R.drawable.baseline_swap_vert_24),
                contentDescription = "Back Arrow",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Sorted manually",
                fontSize = 15.sp,
                color = Color.Gray
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.baseline_edit_24),
            contentDescription = "Back Arrow",
            tint = Color.Black,
            modifier = Modifier.size(26.dp)
        )
    }
}


@Composable
fun ScrollableTabRow(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val context = LocalContext.current

    // List of tabs
    val tabs = listOf(
        "Crypto",
        "Favorites",
        "Popular",
        "Top Movers",
        "Majors",
        "Metals",
        "Indices",
        "Stocks",
        "Energies",
        "Exotic",
        "Minors",
        "All"
    )

    Column {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "Back Arrow",
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { }
            )

            Spacer(modifier = Modifier.padding(end = 5.dp))

            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 0.dp,
                containerColor = Color.White,
                contentColor = Color.Black,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .height(2.dp),
                        color = Color.Black
                    )
                },
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            onTabSelected(index)
                        },
                        text = {
                            Text(
                                title, fontSize = 12.sp
                            )
                        },
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Gray,
                        modifier = Modifier.padding(horizontal = 0.dp)
                    )
                }
            }
        }
    }
}

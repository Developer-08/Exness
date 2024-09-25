package com.testing.exness.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetLayout
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testing.exness.BinanceWebSocket
import com.testing.exness.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PriceAlert(onDismiss: () -> Unit) {
    BackHandler {
        onDismiss()
    }
    var priceAlerts by remember { mutableIntStateOf(0) }
    val addAlertSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var removeAlert by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = addAlertSheetState,
        sheetContent = {
            AddAlertModalBottomSheet(
                onDismiss = { scope.launch { addAlertSheetState.hide() } },
                onPairClick = { priceAlerts = 1 }
            )
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFFAFBFA))
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "",
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    onDismiss()
                                }
                        )
                        Text(text = " Price alerts", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                    Row(
                        modifier = Modifier
                            .size(74.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "",
                            modifier = Modifier
                                .size(28.dp)
                                .clickable { scope.launch { addAlertSheetState.show() } }
                        )
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .size(28.dp)
                                .clickable { }
                        )
                    }
                }

                if (priceAlerts == 0) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No alerts",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Get notified when the price reaches its specified level",
                            fontSize = 14.sp
                        )
                        Button(
                            onClick = { scope.launch { addAlertSheetState.show() } },
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 16.dp),
                            shape = RoundedCornerShape(10),
                            colors = ButtonDefaults.buttonColors(Color(0xFFFFD54F))
                        ) {
                            Text(
                                text = "+ Add new alert", fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                    }
                } else {

                    Column(modifier = Modifier.clickable { removeAlert = true }) {
                        if (removeAlert) {
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "No alerts",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "Get notified when the price reaches its specified level",
                                    fontSize = 14.sp
                                )
                                Button(
                                    onClick = { scope.launch { addAlertSheetState.show() } },
                                    modifier = Modifier
                                        .width(150.dp)
                                        .padding(vertical = 16.dp),
                                    shape = RoundedCornerShape(10),
                                    colors = ButtonDefaults.buttonColors(Color(0xFFFFD54F))
                                ) {
                                    Text(
                                        text = "+ Add new alert", fontSize = 14.sp,
                                        color = Color.Black
                                    )
                                }
                            }
                        } else {
                            Alerts()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Alerts() {
    Text("    XAU/USD", fontWeight = FontWeight.Bold)
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row {
                Text(
                    "Bid at 2525.300 ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text("(+3.1 pips)", color = Color.Gray)
            }

            Row {
                Text("Active ", color = Color.Green)
                Text(
                    "- Until 04 Sep, 01:29",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
    ////////////////////////////////////////////

    Spacer(modifier = Modifier.height(20.dp))
    Text("    BTC/USDT", fontWeight = FontWeight.Bold)
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row {
                Text(
                    "Bid at 65320.300 ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text("(+31.1 pips)", color = Color.Gray)
            }

            Row {
                Text("Active ", color = Color.Green)
                Text(
                    "- Until 01 Sep, 01:29",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }


    /////////////////////////////////////////////////////////////////

    Spacer(modifier = Modifier.height(20.dp))
    Text("    ETH/USDT", fontWeight = FontWeight.Bold)
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row {
                Text(
                    "Bid at 2525.300 ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text("(+72.1 pips)", color = Color.Gray)
            }

            Row {
                Text("Active ", color = Color.Green)
                Text(
                    "- Until 04 Sep, 01:29",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }

    /////////////

    Spacer(modifier = Modifier.height(20.dp))
    Text("    XAU/USD", fontWeight = FontWeight.Bold)
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row {
                Text(
                    "Bid at 2525.300 ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text("(+45.1 pips)", color = Color.Gray)
            }

            Row {
                Text("Active ", color = Color.Green)
                Text(
                    "- Until 04 Sep, 01:29",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }

    ///////////////////////////////////////////////////
    Spacer(modifier = Modifier.height(40.dp))
    Column(modifier = Modifier.height(150.dp)) {
        Image(
            painter = painterResource(id = R.drawable.success),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        Text("Popular content here", fontSize = 14.sp)
    }

}

@Composable
fun AddAlertModalBottomSheet(onDismiss: () -> Unit, onPairClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .background(Color.White)
            .padding(16.dp)
    ) {
        var selectedTab by remember { mutableStateOf("Favourites") }
        Column(modifier = Modifier) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { }
                    )
                }

                listOf(
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
                ).forEach { tabName ->
                    TabItem(
                        tabName = tabName,
                        selectedTab = selectedTab,
                        onTabSelected = { selectedTab = it }
                    )
                }
            }
            HorizontalDivider()
            when (selectedTab) {
                "Favourites" -> Favourites(onCLick = { onPairClick() })
                "Popular" -> Popular(onCLick = { onPairClick() })
                "Majors" -> Majors(onCLick = { onPairClick() })
                else -> {}
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun Favourites(onCLick: () -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
//            .height(150.dp)
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BinanceWebSocket()
    }
}

@Composable
fun Popular(onCLick: () -> Unit) {
    Column(modifier = Modifier.height(150.dp)) {
        Image(
            painter = painterResource(id = R.drawable.pop),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onCLick() }
        )
        Text("Popular content here", fontSize = 14.sp)
    }
}

@Composable
fun Majors(onCLick: () -> Unit) {
    Column(
        modifier = Modifier.height(150.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.pop),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onCLick() }
        )
    }
}

@Composable
fun TabItem(
    tabName: String,
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(end = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = tabName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(6.dp)
                .clickable(
                    onClick = { onTabSelected(tabName) },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            color = if (selectedTab == tabName) Color.Black else Color.Gray
        )
        Column(
            modifier = Modifier
                .width(50.dp)
                .height(2.dp)
                .background(color = if (selectedTab == tabName) Color.Black else Color.White)
        ) { }
    }
}


//@Preview
//@Composable
//fun PA() {
//    PriceAlert()
//}

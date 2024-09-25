package com.testing.exness.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetLayout
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetValue.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.testing.exness.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountsUI(
    navigateToAlertScreen: () -> Unit,
    navigateToNotifications: () -> Unit,
    navigateToDetailScreen: () -> Unit,
    navigateToDepositScreen: () -> Unit,
    navigateToWithdrawScreen: () -> Unit
) {
    val changeAccountTypeSheetState =
        rememberModalBottomSheetState(initialValue = Hidden)
    val selectAccountTypeSheetState =
        rememberModalBottomSheetState(initialValue = Hidden)
    val scope: CoroutineScope = rememberCoroutineScope()
    var selectedTab by remember { mutableStateOf("Open") }


    ModalBottomSheetLayout(
        sheetState = selectAccountTypeSheetState,
        sheetContent = {
            SelectAccountTypeBottomSheet(
                onDismiss = { scope.launch { selectAccountTypeSheetState.hide() } },
                onRealIconClick = {},
                onDemoIconClick = {}
            )
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
//        sheetGesturesEnabled = false // Disable swipe-down gesture
    ) {
        ModalBottomSheetLayout(
            sheetState = changeAccountTypeSheetState,
            sheetContent = {
                ChangeAccountTypeBottomSheet(
                    onDismiss = { scope.launch { changeAccountTypeSheetState.hide() } },
                    onAddClick = { scope.launch { selectAccountTypeSheetState.show() } }
                )
            },
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(color = Color.White)
            ) {
                Column( // for padding only
                    modifier = Modifier.padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Accounts",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Row(
                            modifier = Modifier.width(60.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_alarm_24),
                                contentDescription = "Back Arrow",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable(
                                        onClick = { navigateToAlertScreen() },
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    )
                            )

                            Icon(
                                Icons.Filled.Notifications,
                                contentDescription = "Notifications",
                                modifier = Modifier
                                    .clickable(
                                        onClick = { navigateToNotifications() },
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(color = Color.White)
                                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                                .fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "#181564870",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Filled.KeyboardArrowDown,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clickable {
                                                scope.launch { changeAccountTypeSheetState.show() }
                                            })
                                }
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
                                        modifier = Modifier.padding(
                                            horizontal = 4.dp,
                                            vertical = 2.dp
                                        )
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
                                        modifier = Modifier.padding(
                                            horizontal = 4.dp,
                                            vertical = 2.dp
                                        )
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
                                        modifier = Modifier.padding(
                                            horizontal = 4.dp,
                                            vertical = 2.dp
                                        )
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "23,482.69 USD",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                modifier = Modifier
                                    .padding(start = 50.dp, end = 50.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(
                                    modifier = Modifier
                                        .clickable(
                                            onClick = { navigateToDepositScreen() },
                                            indication = null, // Disables visual feedback on click
                                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                                        ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(45.dp)
                                            .background(
                                                color = Color(0xFFF0F0F0),
                                                shape = RoundedCornerShape(50)
                                            ), contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_arrow_circle_down_24),
                                            contentDescription = "Arrow",
                                            tint = Color.Black,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = "Deposit", fontSize = 12.sp)
                                }
                                Column(
                                    modifier = Modifier
                                        .clickable(
                                            onClick = { navigateToWithdrawScreen() },
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(45.dp)
                                            .background(
                                                color = Color(0xFFF0F0F0),
                                                shape = RoundedCornerShape(50)
                                            ), contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_arrow_circle_up_24),
                                            contentDescription = "Arrow",
                                            tint = Color.Black,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = "Withdraw", fontSize = 12.sp)
                                }

                                Column(
                                    modifier = Modifier
                                        .clickable(
                                            onClick = { navigateToDetailScreen() },
                                            indication = null, // Disables visual feedback on click
                                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                                        ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(45.dp)
                                            .background(
                                                color = Color(0xFFF0F0F0),
                                                shape = RoundedCornerShape(50)
                                            ), contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_format_list_bulleted_24),
                                            contentDescription = "Details",
                                            tint = Color.Black,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = "Details", fontSize = 12.sp)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Column(
                                    modifier = Modifier
                                        .padding(end = 6.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                )
                                {
                                    Text(
                                        text = "Open",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier
                                            .padding(6.dp)
                                            .clickable(
                                                onClick = { selectedTab = "Open" },
                                                indication = null, // Disables visual feedback on click
                                                interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                                            ),
                                        color = if (selectedTab == "Open") Color.Black else Color.Gray
                                    )
                                    Column(
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(3.dp)
                                            .background(color = if (selectedTab == "Open") Color.Black else Color.White)
                                    ) { }
                                }

                                Column(
                                    modifier = Modifier
                                        .padding(end = 6.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Pending",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier
                                            .padding(6.dp)
                                            .clickable(
                                                onClick = { selectedTab = "Pending" },
                                                indication = null, // Disables visual feedback on click
                                                interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                                            ),
                                        color = if (selectedTab == "Pending") Color.Black else Color.Gray
                                    )
                                    Column(
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(3.dp)
                                            .background(color = if (selectedTab == "Pending") Color.Black else Color.White)
                                    ) { }
                                }

                                Column(
                                    modifier = Modifier
                                        .padding(end = 6.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Closed",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier
                                            .padding(6.dp)
                                            .clickable(
                                                onClick = { selectedTab = "Closed" },
                                                indication = null, // Disables visual feedback on click
                                                interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                                            ),
                                        color = if (selectedTab == "Closed") Color.Black else Color.Gray
                                    )
                                    Column(
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(3.dp)
                                            .background(color = if (selectedTab == "Closed") Color.Black else Color.White)
                                    ) { }
                                }
                            }

                            Icon(
                                painter = painterResource(id = R.drawable.baseline_swap_vert_24),
                                contentDescription = "Back Arrow",
                                tint = Color.Gray,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        HorizontalDivider()

                        when (selectedTab) {
                            "Open" -> OpenTab()
                            "Pending" -> PendingTab()
                            "Closed" -> ClosedTab()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun OpenTab() {
    Column(
        modifier = Modifier
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No open orders",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(text = "Use the opportunity to trade on the world's major", fontSize = 14.sp)
        Text(text = "financial markets", fontSize = 14.sp)
    }
}

@Composable
fun PendingTab() {
    Column(
        modifier = Modifier
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No pending orders",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(text = "Use the opportunity to trade on the world's major", fontSize = 14.sp)
        Text(text = "financial markets", fontSize = 14.sp)
    }
}

@Composable
fun ClosedTab() {
    Column(
        modifier = Modifier
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Closed items here",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )
    }
}


@Composable
fun ChangeAccountTypeBottomSheet(
    onDismiss: () -> Unit,
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .height(300.dp)
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(9.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Accounts", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            IconButton(onClick = { onAddClick() }) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        TypeTabRow()
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun SelectAccountTypeBottomSheet(
    onDismiss: () -> Unit,
    onRealIconClick: () -> Unit,
    onDemoIconClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 40.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Text(
            "Select account type", fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            Text("Real Account", fontSize = 14.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Trade with real money and withdraw any profit you \n make.",
                    fontSize = 12.sp, color = Color.Gray
                )
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "",
                    modifier = Modifier
                        .clickable { onRealIconClick() }
                        .size(28.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            Text("Demo Account", fontSize = 14.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Risk-free account. Trade with virtual money.",
                    fontSize = 12.sp, color = Color.Gray
                )
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "",
                    modifier = Modifier
                        .clickable { onDemoIconClick() }
                        .size(28.dp)
                )
            }
        }
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

@Composable
fun TypeTabRow() {
    var selectedTab by remember { mutableStateOf("Demo") }

    Column {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("Real", "Demo", "Archived").forEach { tabName ->
                TabItem(
                    tabName = tabName,
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
            }
        }

        HorizontalDivider()

        when (selectedTab) {
            "Real" -> RealAccount()
            "Demo" -> DemoAccount()
            "Archived" -> Archived()
        }
    }
}

@Composable
fun RealAccount() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("No Real trading accounts yet", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Create account to get superior trading conditions for any strategy.",
            fontSize = 12.sp
        )
    }
}

@Composable
fun DemoAccount() {
    Spacer(modifier = Modifier.height(20.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFF0)),
//      elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "#181564870", fontSize = 14.sp)
                Text(text = "23,482.69 USD", fontSize = 14.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)
            ) {
                // MT5 Label
                Surface(
                    color = Color.LightGray, // Light gray background color
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
                    color = Color.LightGray, // Light gray background color
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
        }
    }
}

@Composable
fun Archived() {
}

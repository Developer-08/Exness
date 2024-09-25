package com.testing.exness.mainscreen

import android.app.Activity
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testing.exness.R
import kotlinx.coroutines.CoroutineScope


@Composable
fun PerformanceScreen() {
    var backPressedOnce by remember { mutableStateOf(false) }
    val context = LocalContext.current

    BackHandler {
        if (backPressedOnce) {
            (context as? Activity)?.finish()
        } else {
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressedOnce = true
        }
    }
    if (backPressedOnce) {
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(2000)
            backPressedOnce = false
        }
    }
    var selectedTabIndex by remember { mutableIntStateOf(0) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFBFA))
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(text = " Performance", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(10.dp))

        // Custom Tab Row
        PerformanceTabs(
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { index -> selectedTabIndex = index }
        )
        Spacer(modifier = Modifier.height(18.dp))

        // Conditionally display UI based on selected tab
        when (selectedTabIndex) {
            0 -> Summary()
            1 -> ExnessBenefits()
        }
    }
}


// Funds/Settings
@Composable
fun PerformanceTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabTitles = listOf("Summary", "Exness benefits")

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
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
        }
    }
}

@Composable
fun Summary(
//    onDeposit: () -> Unit,
//    onWithdraw: () -> Unit
) {
    Column(
        modifier = Modifier
//            .border(1.dp, color = Color.Green)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(50))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "All real accounts",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

            Row(
                modifier = Modifier
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(50))
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Last 7 days",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(80.dp))

        Icon(
            painter = painterResource(id = R.drawable.baseline_warning_amber_24),
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .size(38.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text("No trading activity found", fontWeight = FontWeight.Bold)
        Text("Select different account or period.", fontSize = 14.sp)

        Button(
            onClick = { },
            modifier = Modifier
                .width(100.dp)
                .height(80.dp)
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFD54F))
        ) {
            Text(
                text = "Trade", fontSize = 14.sp,
                color = Color.Black
            )
        }

    }
}

@Composable
fun ExnessBenefits() {
    val scope: CoroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(50))
                    .padding(8.dp)
            ) {
                Text(
                    text = "All real accounts",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Our benefits have saved you",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                painter = painterResource(id = R.drawable.info_24),
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
            )
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_warning_amber_24),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(38.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .width(320.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("You don't have any savings data yet", fontWeight = FontWeight.Bold)
                    Text(
                        "Start trading to see how our better-than-market conditions reduce your trading costs an protect against stop outs.",
                        fontSize = 14.sp, lineHeight = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { },
                    modifier = Modifier
                        .width(150.dp)
                        .height(80.dp)
                        .padding(vertical = 16.dp),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFFD54F))
                ) {
                    Text(
                        text = "Start Trading", fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Available benefits",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(end = 16.dp, top = 16.dp, bottom = 16.dp)
                    .height(60.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = { },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        ),
                    verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(5.dp)
                            .height(16.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(50))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Swap-Free", fontSize = 14.sp)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = { },
                            indication = null, // Disables visual feedback on click
                            interactionSource = remember { MutableInteractionSource() } // Keeps track of interactions
                        ),
                    verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(5.dp)
                            .height(16.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(50))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Negative Balance Protection", fontSize = 14.sp)
                }
            }
        }
    }
}

package com.testing.exness.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetLayout
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Check
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testing.exness.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LeverageUi() {
    var selectedLeverage by remember { mutableStateOf<Leverage?>(null) }
    var tempSelectedLeverage by remember { mutableStateOf<Leverage?>(null) }
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            tempSelectedLeverage?.let { leverage ->
                LeverageWarningBottomSheetContent(
                    leverage = leverage,
                    onDismiss = {
                        selectedLeverage = leverage
                        scope.launch { sheetState.hide() }
                    },
                    onCancel = {
                        tempSelectedLeverage = selectedLeverage // Revert to the original selection
                        scope.launch { sheetState.hide() }
                    }
                )
            }
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFAFBFA))
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                    Text(text = "  Leverage", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                }

                Icon(
                    painter = painterResource(id = R.drawable.baseline_info_outline_24),
                    contentDescription = "Back Arrow",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable { }
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 34.dp, end = 18.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Custom Leverage", fontSize = 14.sp
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Not set  ", fontSize = 14.sp
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

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "LEVERAGE", fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.Gray
                )
                Text(
                    text = "RISK LEVEL", fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.Gray
                )
            }

            // Leverage Options
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(520.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(leverageOptions.size) { index ->
                        val leverage = leverageOptions[index]
                        LeverageItem(
                            leverage = leverage,
                            isSelected = selectedLeverage?.ratio == leverage.ratio,
                            onSelect = {
                                tempSelectedLeverage = leverage
                                scope.launch { sheetState.show() }
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun LeverageWarningBottomSheetContent(
    leverage: Leverage,
    onDismiss: () -> Unit,
    onCancel: () -> Unit
) {
    val message = if (leverage.riskLevel == "High") {
        "You have chosen leverage with a high-risk level. Are you sure you want to continue?"
    } else {
        "Are you sure you want to continue?"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(9.dp))
        Icon(
            painter = painterResource(id = R.drawable.baseline_warning_amber_24),
            contentDescription = "Back Arrow",
            modifier = Modifier
                .size(50.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = if (leverage.ratio == "1:Unlimited") {
                    "1:Unlimited"
                } else {
                    "Leverage ${leverage.ratio} may affect required margin."
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = message,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { onDismiss() },
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD54F)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Ok", color = Color.Black)
        }
        Button(
            onClick = { onCancel() },
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Cancel", color = Color.Black)
        }
    }
}


@Composable
fun LeverageItem(leverage: Leverage, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .selectable(
                selected = isSelected, onClick = onSelect
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            Icon(
                Icons.Default.Check,
                contentDescription = ""
            )
        }

        Text(
            text = if (!isSelected) {
                "        ${leverage.ratio}"
            } else {
                " ${leverage.ratio}"
            },
            modifier = Modifier.weight(1f),
            fontSize = 14.sp
        )
        Text(
            text = leverage.riskLevel, fontSize = 14.sp, color = when (leverage.riskLevel) {
                "High" -> Color.Red
                "Medium" -> Color(0xFFFFA500)
                "Low" -> Color.Green
                else -> Color.Gray
            }
        )
    }
}


data class Leverage(val ratio: String, val riskLevel: String)

val leverageOptions = listOf(
    Leverage("1:Unlimited", "High"),
    Leverage("1:2000", "High"),
    Leverage("1:1000", "High"),
    Leverage("1:800", "High"),
    Leverage("1:600", "High"),
    Leverage("1:500", "High"),
    Leverage("1:400", "High"),
    Leverage("1:200", "High"),
    Leverage("1:100", "Medium"),
    Leverage("1:50", "Medium"),
    Leverage("1:20", "Low"),
    Leverage("1:2", "Low")
)


    
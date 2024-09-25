package com.testing.exness.settings

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ChooseAppearanceScreen() {
    var selectedOption by remember { mutableStateOf("System") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(Color.White)
            .padding(16.dp)
    ) {

        Text(
            "Choose Appearance",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AppearanceOption(
                title = "Light",
                isSelected = selectedOption == "Light",
                onClick = { selectedOption = "Light" }
            )

            AppearanceOption(
                title = "Dark",
                isSelected = selectedOption == "Dark",
                onClick = { selectedOption = "Dark" }
            )

            AppearanceOption(
                title = "System",
                isSelected = selectedOption == "System",
                onClick = { selectedOption = "System" }
            )
        }
    }
}

@Composable
fun AppearanceOption(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 120.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF1F1F1))
                .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) Color.Blue else Color.Gray,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                when (title) {
                    "Light" -> drawLightModeIcon()
                    "Dark" -> drawDarkModeIcon()
                    "System" -> drawSystemModeIcon()
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(title, fontSize = 14.sp)
    }
}

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawLightModeIcon() {
    drawRoundRect(
        color = Color.White,
        cornerRadius = CornerRadius(8.dp.toPx())
    )
    drawRoundRect(
        color = Color.Gray,
        topLeft = androidx.compose.ui.geometry.Offset(0f, size.height / 3),
        size = androidx.compose.ui.geometry.Size(size.width, size.height / 6),
        cornerRadius = CornerRadius(8.dp.toPx())
    )
    drawRoundRect(
        color = Color.Gray,
        topLeft = androidx.compose.ui.geometry.Offset(0f, size.height * 2 / 3),
        size = androidx.compose.ui.geometry.Size(size.width, size.height / 6),
        cornerRadius = CornerRadius(8.dp.toPx())
    )
    drawRoundRect(
        color = Color(0xFF5E5CE5),
        topLeft = androidx.compose.ui.geometry.Offset(0f, size.height * 5 / 6),
        size = androidx.compose.ui.geometry.Size(size.width, size.height / 6),
        cornerRadius = CornerRadius(8.dp.toPx())
    )
}

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawDarkModeIcon() {
    drawRoundRect(
        color = Color.Black,
        cornerRadius = CornerRadius(8.dp.toPx())
    )
    drawRoundRect(
        color = Color.DarkGray,
        topLeft = androidx.compose.ui.geometry.Offset(0f, size.height / 3),
        size = androidx.compose.ui.geometry.Size(size.width, size.height / 6),
        cornerRadius = CornerRadius(8.dp.toPx())
    )
    drawRoundRect(
        color = Color.DarkGray,
        topLeft = androidx.compose.ui.geometry.Offset(0f, size.height * 2 / 3),
        size = androidx.compose.ui.geometry.Size(size.width, size.height / 6),
        cornerRadius = CornerRadius(8.dp.toPx())
    )
    drawRoundRect(
        color = Color(0xFF5E5CE5),
        topLeft = androidx.compose.ui.geometry.Offset(0f, size.height * 5 / 6),
        size = androidx.compose.ui.geometry.Size(size.width, size.height / 6),
        cornerRadius = CornerRadius(8.dp.toPx())
    )
}

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawSystemModeIcon() {
    drawRoundRect(
        color = Color.White,
        topLeft = androidx.compose.ui.geometry.Offset(0f, 0f),
        size = androidx.compose.ui.geometry.Size(size.width / 2, size.height),
        cornerRadius = CornerRadius(8.dp.toPx())
    )
    drawRoundRect(
        color = Color.Black,
        topLeft = androidx.compose.ui.geometry.Offset(size.width / 2, 0f),
        size = androidx.compose.ui.geometry.Size(size.width / 2, size.height),
        cornerRadius = CornerRadius(8.dp.toPx())
    )
    drawRoundRect(
        color = Color.Gray,
        topLeft = androidx.compose.ui.geometry.Offset(0f, size.height / 3),
        size = androidx.compose.ui.geometry.Size(size.width, size.height / 6),
        cornerRadius = CornerRadius(8.dp.toPx())
    )
    drawRoundRect(
        color = Color.Gray,
        topLeft = androidx.compose.ui.geometry.Offset(0f, size.height * 2 / 3),
        size = androidx.compose.ui.geometry.Size(size.width, size.height / 6),
        cornerRadius = CornerRadius(8.dp.toPx())
    )
    drawRoundRect(
        color = Color(0xFF5E5CE5),
        topLeft = androidx.compose.ui.geometry.Offset(0f, size.height * 5 / 6),
        size = androidx.compose.ui.geometry.Size(size.width, size.height / 6),
        cornerRadius = CornerRadius(8.dp.toPx())
    )
}

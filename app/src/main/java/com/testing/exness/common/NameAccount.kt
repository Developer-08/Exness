package com.testing.exness.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NameAccountScreen(onDismiss: () -> Unit) {
    BackHandler { onDismiss() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFBFA))
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(55.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable(
                            onClick = { onDismiss() },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
                Text(text = "   Name Account", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(20.dp))


            OutlinedTextField(
                value = "#16152392",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column {
            Button(
                onClick = { /* Handle turn on push */ },
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD54F)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save changes", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

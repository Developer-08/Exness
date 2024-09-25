package com.testing.exness.auth

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun ResetPassword(onDismiss: () -> Unit) {

    BackHandler { onDismiss() }

    val context = LocalContext.current
    val activity = (context as? Activity)

    // Lock orientation to portrait
    DisposableEffect(Unit) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        onDispose {
            // Reset to default when leaving this screen
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    val darkYellow = Color(0xFFFFD700)
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White), verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Row {
                Text(text = "    ")
                Icon(
                    Icons.Default.Close,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable(
                            onClick = { onDismiss() },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            HorizontalDivider(modifier = Modifier, color = Color.LightGray)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Reset password", fontSize = 28.sp, fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Your email address", fontSize = 12.sp)

            Spacer(modifier = Modifier.height(4.dp))
            BasicTextField(value = email, onValueChange = {
                email = it
                emailError = email.isBlank()
            }, modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .border(
                    1.dp,
                    if (emailError) Color.Red else Color.LightGray,
                    RoundedCornerShape(5.dp),
                )
                .background(Color.White, RoundedCornerShape(5.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),  // Adjust inner padding
                singleLine = true, textStyle = TextStyle(
                    fontSize = 14.sp, color = if (emailError) Color.Red else Color.Black
                ), cursorBrush = SolidColor(Color.Blue), decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        innerTextField()
                    }
                })
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(containerColor = darkYellow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Continue", color = Color.Black)
            }
        }
    }
}

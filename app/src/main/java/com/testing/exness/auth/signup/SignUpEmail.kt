package com.testing.exness.auth.signup

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun SignUpEmailScreen(
    onDismiss: () -> Unit,
    navigateToSignUpPassword: (String) -> Unit
) {
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

    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableIntStateOf(0) }
    val darkYellow = Color(0xFFFFD700)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {

                Row(
                    modifier = Modifier.padding(top = 30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                onClick = { onDismiss() },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    )
                    Text(
                        text = "  Enter your email",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Use this email to sign in to Exness",
                    color = Color.Black,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(30.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Email", fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(4.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(4.dp))
                            .border(
                                1.dp,
                                if (emailError == 0) {
                                    Color.Black
                                } else {
                                    Color.Red
                                }, shape = RoundedCornerShape(4.dp)
                            )
                            .padding(4.dp)
                    ) {
                        BasicTextField(
                            value = email,
                            onValueChange = { email = it },
                            modifier = Modifier
                                .height(30.dp),
                            singleLine = true,
                            decorationBox = { innerTextField ->
                                Row(
                                    Modifier
                                        .background(Color.White),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(modifier = Modifier.weight(1f)) {
                                        innerTextField()
                                    }
                                }
                            }
                        )
                        ////////////////////////////
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    if (emailError == 1) {
                        Text(
                            text = "This field can't be empty",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                if (email.isNotEmpty()) {
                    navigateToSignUpPassword(email)
                } else {
                    emailError = 1
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(containerColor = darkYellow)
        ) {
            Text(text = "Continue", color = Color.Black)
        }

    }
}

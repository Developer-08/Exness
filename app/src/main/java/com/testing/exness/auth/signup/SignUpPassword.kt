package com.testing.exness.auth.signup

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.testing.exness.R
import com.testing.exness.UserPreferences
import kotlinx.coroutines.launch


@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun SignUpPasswordScreen(
    emailId: String,
    onDismiss: () -> Unit,
    navigateToHomeScreen: () -> Unit
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

    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val darkYellow = Color(0xFFFFD700)
    Column(
        modifier = Modifier
            .fillMaxSize()
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
                        text = "  Choose a password",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Password")

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(4.dp))
                            .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                            .padding(4.dp)
                    ) {
                        BasicTextField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier
                                .height(30.dp),
                            singleLine = true,
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            decorationBox = { innerTextField ->
                                Row(
                                    Modifier
                                        .background(Color.White),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(modifier = Modifier.weight(1f)) {
                                        innerTextField()
                                    }
                                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                        val image =
                                            if (passwordVisible) painterResource(id = R.drawable.baseline_visibility_24)
                                            else painterResource(id = R.drawable.baseline_visibility_off_24)
                                        Icon(
                                            painter = image,
                                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                        )
                                    }
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "${password.length}/15",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }

                    // Password requirement list
                    PasswordCriteria(
                        "Use from 8 to 15 characters",
                        isMet = password.length in 8..15
                    )
                    PasswordCriteria(
                        "Use both uppercase and lowercase letters",
                        isMet = password.any { it.isUpperCase() } && password.any { it.isLowerCase() }
                    )
                    PasswordCriteria(
                        "Use a combination of numbers and English letters and supported special characters",
                        isMet = password.any { it.isDigit() } && password.any { !it.isLetterOrDigit() }
                    )
                }



            }
        }
//        Text(text = emailId)

        Button(
            onClick = {
                if (emailId.isBlank() || password.isBlank()) {
                    // if either field is empty
                    Toast.makeText(
                        context,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    coroutineScope.launch {
                        val userPreferences = UserPreferences(context)
                        userPreferences.saveUserData(email = emailId, password = password)
                        Toast.makeText(context, "Signup Successful", Toast.LENGTH_SHORT).show()
                        navigateToHomeScreen()
                    }
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


@Composable
fun PasswordCriteria(text: String, isMet: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            tint = if (isMet) Color.Green else Color.Gray,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                color = if (isMet) Color.Green else Color.Gray,
                fontSize = 12.sp
            )
        )
    }
}


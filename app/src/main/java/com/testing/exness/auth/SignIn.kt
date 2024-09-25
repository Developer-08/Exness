package com.testing.exness.auth

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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.testing.exness.R
import com.testing.exness.UserPreferences
import kotlinx.coroutines.launch


@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun SignIn(
    navigateToForgotPassword: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    onDismiss: () -> Unit
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

    val coroutineScope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    val cornerRadius = 5.dp

    val darkYellow = Color(0xFFFFD700)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .background(color = Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(top = 40.dp, bottom = 35.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "",
                    modifier = Modifier
                        .clickable(
                            onClick = { onDismiss() },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
                Text(
                    text = "   Sign In", fontSize = 24.sp, fontWeight = FontWeight.SemiBold
                )
            }
            Text(text = "Please enter your email address and password")

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Email", fontSize = 12.sp)
            Spacer(modifier = Modifier.height(4.dp))
            BasicTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = email.isBlank()
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(
                        1.dp,
                        if (emailError) Color.Red else Color.LightGray,
                        RoundedCornerShape(cornerRadius),
                    )
                    .background(Color.White, RoundedCornerShape(cornerRadius))
                    .padding(horizontal = 8.dp, vertical = 4.dp),  // Adjust inner padding
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = if (emailError) Color.Red else Color.Black
                ),
                cursorBrush = SolidColor(Color.Blue),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        innerTextField()  // This will render the actual text
                    }
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            if (emailError) {
                Text(
                    text = "This field cannot be empty",
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Password", fontSize = 12.sp)
            Spacer(modifier = Modifier.height(4.dp))
            BasicTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = password.isBlank()
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(
                        1.dp,
                        if (passwordError) Color.Blue else Color.LightGray,
                        RoundedCornerShape(5.dp)
                    )
                    .background(
                        Color.White,
                        RoundedCornerShape(5.dp)
                    ) // background color with rounded corners
                    .padding(horizontal = 8.dp, vertical = 4.dp), // Inner padding
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = if (passwordError) Color.Red else Color.Black
                ),
                cursorBrush = SolidColor(Color.Blue),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(), // Password hiding/showing logic
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        innerTextField() // Render the actual text
                        Spacer(modifier = Modifier.weight(1f)) // Push the trailing icon to the end

                        // Trailing icon for password visibility toggle
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            val image = if (passwordVisible) R.drawable.baseline_visibility_24
                            else R.drawable.baseline_visibility_off_24

                            Icon(painter = painterResource(id = image), contentDescription = null)
                        }
                    }
                }
            )


        }

        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        // if either field is empty
                        Toast.makeText(
                            context,
                            "Please enter both email and password",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        coroutineScope.launch {
                            val userPreferences = UserPreferences(context)
                            val user = userPreferences.getUserData(email)

                            // Validate user
                            if (user != null && user.password == password) {
//                                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT)
//                                    .show()
                                navigateToHomeScreen()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Invalid email & password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                },
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(containerColor = darkYellow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign In", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(18.dp))

            Text(text = "I forgot my password",
                Modifier.clickable {
                    navigateToForgotPassword()
                })
        }
    }
}

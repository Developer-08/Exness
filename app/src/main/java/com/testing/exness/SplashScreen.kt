package com.testing.exness

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext



@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun SplashScreen(
    navigateToSignInScreen: () -> Unit,
    navigateToResidenceScreen: () -> Unit
) {
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
    val lightGray = Color(0xFFE8E8E8)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.End
        ) {
            val url = "https://get.exness.help/hc/en-us"

            Icon(
                painter = painterResource(id = R.drawable.info_24),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
                    .clickable(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
            )
        }

        Spacer(modifier = Modifier.height(180.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(270.dp))

        Button(
            onClick = { navigateToResidenceScreen() },
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(containerColor = darkYellow),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register", color = Color.Black)
        }
        Spacer(modifier = Modifier.height(4.dp))

        Button(
            onClick = { navigateToSignInScreen() },
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(containerColor = lightGray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sign In", color = Color.Black)
        }
    }
}


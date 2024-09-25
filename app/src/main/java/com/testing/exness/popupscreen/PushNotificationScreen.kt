package com.testing.exness.popupscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import com.testing.exness.R


@Composable
fun PushNotificationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(55.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Secure your funds",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Use push notifications as a faster and more secure verification method",
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }

//        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier,
//                .border(1.dp, color = Color.Blue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.exnesss),
                contentDescription = null,
                modifier = Modifier
                    .size(350.dp)
//                .border(1.dp, color = Color.Red)
            )

            Button(
                onClick = { /* Handle turn on push */ },
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD54F)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Turn on push", color = Color.Black)
            }
            TextButton(onClick = { /* Handle not now */ }) {
                Text(text = "Not now", color = Color.Gray)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PushNotificationScreenPreview() {
    PushNotificationScreen()
}

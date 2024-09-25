package com.testing.exness.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.testing.exness.R

@Composable
fun CusLeverageScreen() {
    var cusLeverage by remember { mutableStateOf("1:100") }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFFFAFBFA))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {

                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { }
                    )
                    Text(text = " Custom Leverage", fontSize = 24.sp)
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

            Spacer(modifier = Modifier.height(50.dp))

            Column(
                modifier = Modifier.padding(8.dp)
            ) {

                OutlinedTextField(
                    value = cusLeverage,
                    onValueChange = { cusLeverage = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(5.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "1:2 - 1:2 000 000 000",
                        fontSize = 12.sp
                    )
                }
            }
        }

        Box(modifier = Modifier.padding(8.dp)) {
            Button(
                onClick = { /* Continue action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(Color(0xFFFFD54F))
            ) {
                Text(
                    text = "Save Changes", fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCLScreen() {
    CusLeverageScreen()
}

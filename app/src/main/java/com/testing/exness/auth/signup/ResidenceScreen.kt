package com.testing.exness.auth.signup

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.net.Uri
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.testing.exness.CountryViewModel


@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun ResidenceScreen(
    viewModel: CountryViewModel = viewModel(),
    navigateToSplashScreen: () -> Unit,
    navigateToSelectCountry: () -> Unit,
    navigateToSignUpEmail: () -> Unit
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

    val selectedCountry by viewModel.selectedCountry
    var isDeclarationChecked by remember { mutableStateOf(false) }
    var residenceCheckColor by remember { mutableStateOf("Black") }

    val darkYellow = Color(0xFFFFD700)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier, verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.height(100.dp))

                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable(
                            onClick = { navigateToSplashScreen() },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
                Text(
                    text = "  Your residence", fontSize = 24.sp, fontWeight = FontWeight.SemiBold
                )
            }

            Text(text = "Select your residence", color = Color.Black, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(34.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = { navigateToSelectCountry() },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Show selected country here
                if (selectedCountry != null) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Display country flag (image)
                        Image(
                            painter = rememberAsyncImagePainter(selectedCountry?.flags?.png),
                            contentDescription = "Country flag",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        // Display country name
                        Text(
                            text = selectedCountry?.name?.common ?: "Country not selected",
                            fontSize = 16.sp, color = Color.Black
                        )
                    }
                } else {
                    Text(
                        text = "Country / region",
                        fontSize = 16.sp
                    )
                }

                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "",
                    modifier = Modifier
                        .clickable(
                            onClick = { navigateToSelectCountry() },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
            }

        }

        Spacer(modifier = Modifier.height(430.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isDeclarationChecked,
                onCheckedChange = { isDeclarationChecked = it })
            Text(text = "I declare and confirm that I am not a citizen or resident of the US for tax purposes.",
                color = if (residenceCheckColor == "Black"){
                    Color.Black
                } else {
                    Color.Red
                }
            )
        }

        Button(
            onClick = {
                if (isDeclarationChecked) {
                    navigateToSignUpEmail()
                } else {
                    residenceCheckColor = "Red"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(containerColor = darkYellow)
        ) {
            Text(text = "Continue", color = Color.Black)
        }

        Column {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Based on the selected country of residence, you are registering with Exness, regulated by the Seychelles FSA",
                fontSize = 12.sp, style = TextStyle(lineHeight = 18.sp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            ClickableText()
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "You also confirm that you fully understand the nature and the risks of the services and products envisaged. Trading CFDs is not suitable for everyone; it should be done by traders with a high risk tolerance and who can afford potential losses.",
                fontSize = 12.sp, style = TextStyle(lineHeight = 18.sp)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun ClickableText() {
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        append("By clicking Continue, you confirm that you have read, understood, and agree with all the information in the ")

        pushStringAnnotation(
            tag = "Client Agreement", annotation = "https://example.com/client-agreement"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue, textDecoration = TextDecoration.Underline,
                fontSize = 12.sp
            )
        ) {
            append("Client Agreement")
        }
        pop()

        append(" and the service terms and conditions listed in the following documents: ")

        pushStringAnnotation(
            tag = "General Business Terms",
            annotation = "https://example.com/general-business-terms"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue, textDecoration = TextDecoration.Underline,
                fontSize = 12.sp
            )
        ) {
            append("General Business Terms")
        }
        pop()

        append(", ")

        pushStringAnnotation(
            tag = "Partnership Agreement", annotation = "https://example.com/partnership-agreement"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue, textDecoration = TextDecoration.Underline,
                fontSize = 12.sp
            )
        ) {
            append("Partnership Agreement")
        }
        pop()

        append(", ")

        pushStringAnnotation(
            tag = "Privacy Policy", annotation = "https://example.com/privacy-policy"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue, textDecoration = TextDecoration.Underline,
                fontSize = 12.sp
            )
        ) {
            append("Privacy Policy")
        }
        pop()

        append(", ")

        pushStringAnnotation(
            tag = "Risk Disclosure and Warning Notice",
            annotation = "https://example.com/risk-disclosure"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue, textDecoration = TextDecoration.Underline,
                fontSize = 12.sp
            )
        ) {
            append("Risk Disclosure and Warning Notice")
        }
        pop()

        append(", and ")

        pushStringAnnotation(
            tag = "Key Facts Statement", annotation = "https://example.com/key-facts-statement"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue, textDecoration = TextDecoration.Underline,
                fontSize = 12.sp
            )
        ) {
            append("Key Facts Statement")
        }
        pop()
    }

    ClickableText(text = annotatedString,
        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
        onClick = { offset ->
            annotatedString.getStringAnnotations(start = offset, end = offset).firstOrNull()
                ?.let { annotation ->
                    // Handle the click on the text, open the link in a browser
                    openUrl(context, annotation.item)
                }
        })
}

fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

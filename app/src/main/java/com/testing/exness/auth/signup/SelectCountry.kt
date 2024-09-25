package com.testing.exness.auth.signup

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.testing.exness.Country
import com.testing.exness.CountryViewModel
import com.testing.exness.R

@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun Country(onDismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 16.dp, end = 16.dp, top = 50.dp)
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
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
                    text = "  Country / region", fontSize = 24.sp, fontWeight = FontWeight.SemiBold
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "Back Arrow",
                tint = Color.Black,
                modifier = Modifier
                    .size(32.dp)
                    .clickable(
                        onClick = { },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        CountryListScreen()
    }
}


@Composable
fun CountryListScreen(viewModel: CountryViewModel = viewModel()) {
    val countries by viewModel.countries
//    val selectedCountry by viewModel.selectedCountry

    Column {
        LazyColumn {
            items(countries) { country ->
                CountryItem(country, onClick = { viewModel.selectCountry(country) })
            }
        }
//        // Display selected country details if any
//        selectedCountry?.let {
//            SelectedCountryDetail(country = it)
//        }
    }
}

@Composable
fun CountryItem(country: Country, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                onClick = { onClick() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // flag image using Coil
        Image(
            painter = rememberAsyncImagePainter(country.flags.png),
            contentDescription = "${country.name.common} flag",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = country.name.common, fontSize = 16.sp)
    }
}


@Composable
fun SelectedCountryDetail(country: Country) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Selected Country: ${country.name.common}", fontSize = 20.sp)
        Image(
            painter = rememberAsyncImagePainter(country.flags.png),
            contentDescription = "${country.name.common} flag",
            modifier = Modifier.size(100.dp)
        )
    }
}
package com.testing.exness

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.testing.exness.auth.ResetPassword
import com.testing.exness.auth.SignIn
import com.testing.exness.auth.signup.Country
import com.testing.exness.auth.signup.ResidenceScreen
import com.testing.exness.auth.signup.SignUpEmailScreen
import com.testing.exness.auth.signup.SignUpPasswordScreen
import com.testing.exness.mainscreen.HomeUi
import com.testing.exness.ui.theme.MyAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CryptoApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun CryptoApp(modifier: Modifier = Modifier) {

    var token by remember { mutableStateOf<String?>(null) }
    // Obtain the FCM token
    LaunchedEffect(Unit) {
        // Get the FCM token asynchronously
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                token = task.result
                // Use the token if needed
            } else {
                // Handle the error
            }
        }
    }

    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Routes.SplashScreen
    ) {

        // Splash
        composable(Routes.SplashScreen) {
            SplashScreen(
                navigateToSignInScreen = {
                    navController.navigate(Routes.SIGNIN)
                },
                navigateToResidenceScreen = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }
        // Splash -> SignIn
        composable(Routes.SIGNIN) {
            SignIn(
                onDismiss = {
                    navController.navigate(Routes.SplashScreen) {
                        popUpTo(Routes.SIGNIN) { inclusive = true }
                    }
                },
                navigateToForgotPassword = {
                    navController.navigate(Routes.FORGOTPASSWORD) {
                        popUpTo(Routes.SIGNIN) { inclusive = true }
                    }
                },
                navigateToHomeScreen = {
                    navController.navigate(Routes.HOMESCREEN) {
                        popUpTo(Routes.SIGNIN) { inclusive = true }
                    }
                }
            )
        }
        // Splash -> SignIn -> ForgotPassword
        composable(Routes.FORGOTPASSWORD) {
            ResetPassword(
                onDismiss = {
                    navController.navigate(Routes.SIGNIN) {
                        popUpTo(Routes.FORGOTPASSWORD) { inclusive = true }
                    }
                }
            )
        }


        ////////////////////////////////////////////////////////////////////////////////////////////////

        // Splash -> SignUp
        composable(Routes.REGISTER) {
            ResidenceScreen(
                navigateToSplashScreen = { navController.navigate(Routes.SplashScreen) },
                navigateToSelectCountry = { navController.navigate(Routes.SELECTREGION) },
                navigateToSignUpEmail = { navController.navigate(Routes.SIGNUPEMAIL) }
            )
        }
        // SignUp -> Country
        composable(Routes.SELECTREGION) {
            Country(
                onDismiss = {
                    navController.navigate(Routes.REGISTER) {
                        popUpTo(Routes.SELECTREGION) { inclusive = true }
                    }
                }
            )
        }
        // SignUp -> Country -> Email
        composable(Routes.SIGNUPEMAIL) {
            SignUpEmailScreen(
                onDismiss = {
                    navController.navigate(Routes.REGISTER) {
                        popUpTo(Routes.SIGNUPEMAIL) { inclusive = true }
                    }
                },
                navigateToSignUpPassword = { email ->
                    navController.navigate("SIGNUPPASSWORD/$email") {
                        popUpTo(Routes.SIGNUPEMAIL) { inclusive = true }
                    }
                }
            )
        }
        // SignUp -> Country -> Email -> Password
        composable(route = "SIGNUPPASSWORD/{email}") {
            val email = it.arguments?.getString("email") ?: "no Email"
            SignUpPasswordScreen(
                emailId = email,
                onDismiss = {
                    navController.navigate(Routes.SIGNUPEMAIL) {
                        popUpTo(Routes.SIGNUPPASSWORD) { inclusive = true }
                    }
                },
                navigateToHomeScreen = {
                    navController.navigate(Routes.HOMESCREEN) {
                        popUpTo(Routes.SIGNUPPASSWORD) { inclusive = true }
                    }
                }
            )
        }

        ////////////////////////////////////////////////////////////////////////////////////////////

        // Home Screen
        composable(Routes.HOMESCREEN) {
            HomeUi(navController = navController)
        }

    }
}


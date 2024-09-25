package com.testing.exness.mainscreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.testing.exness.R
import com.testing.exness.Routes
import com.testing.exness.common.PriceAlert
import com.testing.exness.common.DetailScreen
import com.testing.exness.common.DepositScreen
import com.testing.exness.common.WDScreen
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.DisposableEffect
import com.testing.exness.HelpCenter
import com.testing.exness.common.NameAccountScreen
import com.testing.exness.settings.ChangePasscodeScreen
import com.testing.exness.settings.SecureAccountScreen
import com.testing.exness.settings.Language
import com.testing.exness.settings.Notification
import com.testing.exness.settings.NotificationSetting
import com.testing.exness.settings.Settings
import com.testing.exness.settings.TradingTerminal


@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun HomeUi(
    navController: NavController
) {
    val controller: NavController = rememberNavController()
    val currentRoute = controller.currentBackStackEntryAsState().value?.destination?.route
    val context = LocalContext.current

    var backPressedOnce by remember { mutableStateOf(false) }

    BackHandler(enabled = currentRoute == Routes.AccountsUi) {
        if (backPressedOnce) {
            (context as? Activity)?.finish() // Close the app on the second back press
        } else {
            // Show a Toast message on the first back press
            backPressedOnce = true
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
    }

    // Reset the back press state after 2 seconds
    if (backPressedOnce) {
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(2000) // Delay for 2 seconds
            backPressedOnce = false
        }
    }

    Scaffold(
        topBar = {},
        bottomBar = {
            if (
                currentRoute == Routes.AccountsUi || currentRoute == Routes.TradeScreen
                || currentRoute == Routes.MarketScreen || currentRoute == Routes.Performance
                || currentRoute == Routes.ProfileScreen
            ) {
                BottomNavigationBar(navController = controller)
            }
        }
    ) { innerPadding ->
        Navigate(navController = controller, pd = innerPadding,
            onLogout = {
                // Navigate to the splash screen without dealing with tokens or flags
                navController.navigate(Routes.SplashScreen) {
                    popUpTo(Routes.HOMESCREEN) { inclusive = true } // Clear the backstack
                }
            })

        if (currentRoute == Routes.DepositScreen || currentRoute == Routes.Withdraw) {

            val activity = (context as? Activity)

            // Lock orientation to landscape
            DisposableEffect(Unit) {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                onDispose {
                    // Reset to default when leaving this screen
                    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }
            }
        } else {
            val activity = (context as? Activity)

            // Lock orientation to portrait
            DisposableEffect(Unit) {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                onDispose {
                    // Reset to default when leaving this screen
                    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Gray
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.baseline_dashboard_24),
                    contentDescription = "Accounts"
                )
            },
            label = { Text("Accounts", fontSize = 10.sp) },
            selected = true,
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.Gray,
            onClick = {
                navController.navigate(Routes.AccountsUi) {
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.baseline_candlestick_chart_24),
                    contentDescription = "Trade"
                )
            },
            label = { Text("Trade", fontSize = 10.sp) },
            selected = false,
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.Gray,
            onClick = {
                navController.navigate(Routes.TradeScreen) {
                    launchSingleTop =
                        true  // Prevents multiple instances of TradeScreen in the backstack
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.baseline_blur_circular_24),
                    contentDescription = "Markets"
                )
            },
            label = { Text("Markets", fontSize = 10.sp) },
            selected = false,
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.Gray,
            onClick = {
                navController.navigate(Routes.MarketScreen) {
                    launchSingleTop =
                        true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.baseline_bar_chart_24),
                    contentDescription = "Performance"
                )
            },
            label = { Text("Performance", fontSize = 9.sp) },
            selected = false,
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.Gray,
            onClick = {
                navController.navigate(Routes.Performance) {
                    launchSingleTop =
                        true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Profile")
            },
            label = { Text("Profile", fontSize = 10.sp) },
            selected = false,
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.Gray,
            onClick = {
                navController.navigate(Routes.ProfileScreen) {
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )
    }
}


@Composable
fun Navigate(navController: NavController, pd: PaddingValues, onLogout: () -> Unit) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Routes.AccountsUi,
        modifier = Modifier.padding(pd)
    ) {

        // AccountsUi
        composable(Routes.AccountsUi) {
            AccountsUI(
                navigateToAlertScreen = {
                    navController.navigate(Routes.Alerts) {
                        popUpTo(Routes.AccountsUi) { inclusive = true }
                    }
                },
                navigateToDetailScreen = {
                    navController.navigate(Routes.Details) {
                        popUpTo(Routes.AccountsUi) { inclusive = true }
                    }
                },
                navigateToDepositScreen = {
                    navController.navigate(Routes.DepositScreen) {
                        popUpTo(Routes.AccountsUi) { inclusive = true }
                    }
                },
                navigateToWithdrawScreen = {
                    navController.navigate(Routes.Withdraw) {
                        popUpTo(Routes.AccountsUi) { inclusive = true }
                    }
                },
                navigateToNotifications = {
                    navController.navigate(Routes.Notifications) {
                        popUpTo(Routes.AccountsUi) { inclusive = true }
                    }
                }
            )
        }
        // AccountsUi -> Deposit
        composable(Routes.DepositScreen) {
            DepositScreen(onDismiss = {
                navController.navigate(Routes.AccountsUi) {
                    popUpTo(Routes.DepositScreen) { inclusive = true }
                }
            })
        }
        // AccountsUi -> WDScreen
        composable(Routes.Withdraw) {
            WDScreen(onDismiss = {
                navController.navigate(Routes.AccountsUi) {
                    popUpTo(Routes.Withdraw) { inclusive = true }
                }
            })
        }
        // AccountsUi -> Details Screen in AccountsUi
        composable(Routes.Details) {
            DetailScreen(onDismiss = {
                navController.navigate(Routes.AccountsUi) {
                    popUpTo(Routes.Details) { inclusive = true }
                }
            },
                navigateToDepositScreen = {
                    navController.navigate(Routes.DepositToAccoutsDetails) {
                        popUpTo(Routes.Details) { inclusive = true }
                    }
                },
                navigateToWithdrawScreen = {
                    navController.navigate(Routes.WithdrawToAccountsDetails) {
                        popUpTo(Routes.Details) { inclusive = true }
                    }
                },
                navigateToNameAccount = {
                    navController.navigate(Routes.NAMEACCOUNT) {
                        popUpTo(Routes.Details) { inclusive = true }
                    }
                }
            )
        }
        // AccountsUi -> WD Screen in Details Screen
        composable(Routes.WithdrawToAccountsDetails) {
            WDScreen(onDismiss = {
                navController.navigate(Routes.Details) {
                    popUpTo(Routes.WithdrawToAccountsDetails) { inclusive = true }
                }
            })
        }
        // AccountsUi -> Deposit Screen in Details Screen
        composable(Routes.DepositToAccoutsDetails) {
            DepositScreen(onDismiss = {
                navController.navigate(Routes.Details) {
                    popUpTo(Routes.DepositToAccoutsDetails) { inclusive = true }
                }
            })
        }
        // AccountsUi -> Alerts Screen
        composable(Routes.Alerts) {
            PriceAlert(onDismiss = {
                navController.navigate(Routes.AccountsUi) {
                    popUpTo(Routes.Alerts) { inclusive = true }
                }
            })
        }
        // AccountsUi -> Alerts Screen
        composable(Routes.Notifications) {
            Notification(onDismiss = {
                navController.navigate(Routes.AccountsUi) {
                    popUpTo(Routes.Notifications) { inclusive = true }
                }
            })
        }
        // AccountsUi -> Name Account i settingsUi
        composable(Routes.NAMEACCOUNT) {
            NameAccountScreen(onDismiss = {
                navController.navigate(Routes.Details) {
                    popUpTo(Routes.NAMEACCOUNT) { inclusive = true }
                }
            })
        }
        ////////////////////////////////////////////////////////////////////////////////////


        // Trade Screen
        composable(Routes.TradeScreen) {
            TradeScreen(
                navigateToAlert = {
                    navController.navigate(Routes.AlertScreenInTradeScreen) {
                        popUpTo(Routes.TradeScreen) { inclusive = true }
                    }
                },
                navigateToDetails = {
                    navController.navigate(Routes.DetailsScreenInTradeScreen) {
                        popUpTo(Routes.TradeScreen) { inclusive = true }
                    }
                }
            )
        }
        // Trade Screen - Alerts
        composable(Routes.AlertScreenInTradeScreen) {
            PriceAlert(onDismiss = {
                navController.navigate(Routes.TradeScreen) {
                    popUpTo(Routes.AlertScreenInTradeScreen) { inclusive = true }
                }
            })
        }
        // Trade Screen - Details
        composable(Routes.DetailsScreenInTradeScreen) {
            DetailScreen(
                onDismiss = {
                navController.navigate(Routes.TradeScreen) {
                    popUpTo(Routes.DetailsScreenInTradeScreen) { inclusive = true }
                }
            },
                navigateToDepositScreen = {
                    navController.navigate(Routes.DepositToTradeScreenDetails) {
                        popUpTo(Routes.DetailsScreenInTradeScreen) { inclusive = true }
                    }
                },
                navigateToWithdrawScreen = {
                    navController.navigate(Routes.WithdrawToTradeScreenDetails) {
                        popUpTo(Routes.DetailsScreenInTradeScreen) { inclusive = true }
                    }
                },
                navigateToNameAccount = {} ///////////////////////////////////////////////////////////////////////////////
            )
        }
        // TradeScreen -> DepositScreen -> DetailScreen
        composable(Routes.DepositToTradeScreenDetails) {
            DepositScreen(onDismiss = {
                navController.navigate(Routes.DetailsScreenInTradeScreen) {
                    popUpTo(Routes.DepositToTradeScreenDetails) { inclusive = true }
                }
            })
        }
        // TradeScreen -> WithdrawScreen -> DetailScreen
        composable(Routes.WithdrawToTradeScreenDetails) {
            WDScreen(onDismiss = {
                navController.navigate(Routes.DetailsScreenInTradeScreen) {
                    popUpTo(Routes.WithdrawToTradeScreenDetails) { inclusive = true }
                }
            })
        }
        ////////////////////////////////////////////////////////////////////////////////////


        // Market Screen
        composable(Routes.MarketScreen) {
            MarketScreen(
                navigateToDetails = {
                    navController.navigate(Routes.DetailsScreenInMarketScreen) {
                        popUpTo(Routes.MarketScreen) { inclusive = true }
                    }
                }
            )
        }
        // Market Screen - Details
        composable(Routes.DetailsScreenInMarketScreen) {
            DetailScreen(onDismiss = {
                navController.navigate(Routes.MarketScreen)  {
                    popUpTo(Routes.DetailsScreenInMarketScreen) { inclusive = true }
                }
            },
                navigateToDepositScreen = {
                    navController.navigate(Routes.DepositToMarketScreenDetails) {
                        popUpTo(Routes.DetailsScreenInMarketScreen) { inclusive = true }
                    }
                },
                navigateToWithdrawScreen = {
                    navController.navigate(Routes.WithdrawToMarketScreenDetails) {
                        popUpTo(Routes.DetailsScreenInMarketScreen) { inclusive = true }
                    }
                },
                navigateToNameAccount = {} ///////////////////////////////////////////////////////////////////////////////
            )
        }
        // MarketScreen -> DepositScreen -> DetailScreen
        composable(Routes.DepositToMarketScreenDetails) {
            DepositScreen(onDismiss = {
                navController.navigate(Routes.DetailsScreenInMarketScreen) {
                    popUpTo(Routes.DepositToMarketScreenDetails) { inclusive = true }
                }
            })
        }
        // MarketScreen -> WithdrawScreen -> DetailScreen
        composable(Routes.WithdrawToMarketScreenDetails) {
            WDScreen(onDismiss = {
                navController.navigate(Routes.DetailsScreenInMarketScreen) {
                    popUpTo(Routes.WithdrawToMarketScreenDetails) { inclusive = true }
                }
            })
        }
        ////////////////////////////////////////////////////////////////////////////////////

        // Performance Screen
        composable(Routes.Performance) {
            PerformanceScreen()
        }

        ////////////////////////////////////////////////////////////////////////////////////

        // Profile Screen
        composable(Routes.ProfileScreen) {
            ProfileScreen(
                navigateToSettings = {
                    navController.navigate(Routes.SETTINGS) {
                        popUpTo(Routes.ProfileScreen) { inclusive = true }
                    }
                },
                navigateToHelpCenter = {
                    navController.navigate(Routes.HELPCENTER) {
                        popUpTo(Routes.ProfileScreen) { inclusive = true }
                    }
                },
                onLogout = {
                    onLogout()
                } // ----------------------------------------------------------------------------------------------------
            )
        }
        // Profile -> HELPCENTER
        composable(Routes.HELPCENTER) {
            HelpCenter (
                onDismiss = {
                    navController.navigate(Routes.ProfileScreen) {
                        popUpTo(Routes.HELPCENTER) { inclusive = true }
                    }
                }
            )
        }
        // Profile -> Settings
        composable(Routes.SETTINGS) {
            Settings(onDismiss = {
                navController.navigate(Routes.ProfileScreen) {
                    popUpTo(Routes.SETTINGS) { inclusive = true }
                }
            },
                navigateToLanguage = {
                    navController.navigate(Routes.LANGUAGE) {
                        popUpTo(Routes.SETTINGS) { inclusive = true }
                    }
                },
                navigateToTerminal = {
                    navController.navigate(Routes.TERMINAL) {
                        popUpTo(Routes.SETTINGS) { inclusive = true }
                    }
                },
                navigateToNotifications = {
                    navController.navigate(Routes.SETTINGSNOTIFICATION) {
                        popUpTo(Routes.SETTINGS) { inclusive = true }
                    }
                },
                navigateToSecureAccount = {
                    navController.navigate(Routes.SECUREACCOUNT) {
                        popUpTo(Routes.SETTINGS) { inclusive = true }
                    }
                },
                navigateToChangePasscode = {
                    navController.navigate(Routes.CHANGEPASSCODE) {
                        popUpTo(Routes.SETTINGS) { inclusive = true }
                    }
                }
            )
        }
        // Profile -> Settings -> CHANGE PASSCODE
        composable(Routes.CHANGEPASSCODE) {
            ChangePasscodeScreen(
                onDismiss = {
                    navController.navigate(Routes.SETTINGS) {
                        popUpTo(Routes.CHANGEPASSCODE) { inclusive = true }
                    }
                }
            )
        }
        // Profile -> Settings -> SECUREACCOUNT
        composable(Routes.SECUREACCOUNT) {
            SecureAccountScreen(
                onDismiss = {
                    navController.navigate(Routes.SETTINGS) {
                        popUpTo(Routes.SECUREACCOUNT) { inclusive = true }
                    }
                }
            )
        }
        // Profile -> Settings -> Terminal
        composable(Routes.TERMINAL) {
            TradingTerminal(
                onDismiss = {
                    navController.navigate(Routes.SETTINGS) {
                        popUpTo(Routes.TERMINAL) { inclusive = true }
                    }
                }
            )
        }
        // Profile -> Settings -> Notifications
        composable(Routes.SETTINGSNOTIFICATION) {
            NotificationSetting(
                onDismiss = {
                    navController.navigate(Routes.SETTINGS) {
                        popUpTo(Routes.SETTINGSNOTIFICATION) { inclusive = true }
                    }
                }
            )
        }
        // Profile -> Settings -> Language
        composable(Routes.LANGUAGE) {
            Language(
                onDismiss = {
                    navController.navigate(Routes.SETTINGS) {
                        popUpTo(Routes.LANGUAGE) { inclusive = true }
                    }
                }
            )
        }


        ////////////////////////////////////////////////////////////////////////////////////
    }
}


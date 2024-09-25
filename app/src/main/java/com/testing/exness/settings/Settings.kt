package com.testing.exness.settings

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetLayout
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Delete
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testing.exness.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Settings(
    onDismiss: () -> Unit,
    navigateToNotifications: () -> Unit,
    navigateToLanguage: () -> Unit,
    navigateToTerminal: () -> Unit,
    navigateToSecureAccount: () -> Unit,
    navigateToChangePasscode:()-> Unit
) {
    BackHandler {
        onDismiss()
    }

    val deleteAccountSheetState =
        rememberModalBottomSheetState(initialValue = Hidden)
    val appearanceSheetState =
        rememberModalBottomSheetState(initialValue = Hidden)

    val scope: CoroutineScope = rememberCoroutineScope()


    ModalBottomSheetLayout(
        sheetState = deleteAccountSheetState,
        sheetContent = {
            AccountDeleteBottomSheet()
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        ModalBottomSheetLayout(
            sheetState = appearanceSheetState,
            sheetContent = {
                AppearanceBottomSheet()
            },
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xFFFAFBFA))
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "",
                            modifier = Modifier
                                .size(32.dp)
                                .clickable(
                                    onClick = { onDismiss() },
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                        )
                        Text(text = "   Settings", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
//                SettingsDesign()
                    //////////////////////////////////////////////////////////SettingsDesign

                    Spacer(modifier = Modifier.height(30.dp))

                    // PREFERENCES
                    Column {
                        Text(
                            text = " PREFERENCES", color = Color.Gray, fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            ) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier
                                        .clickable(
                                            onClick = { navigateToTerminal() },
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        )
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Trading terminal", fontSize = 14.sp
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text("Exness")
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                            contentDescription = "Arrow",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(22.dp)
                                                .clickable(
                                                    onClick = { navigateToTerminal() },
                                                    indication = null,
                                                    interactionSource = remember { MutableInteractionSource() }
                                                )
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier
                                        .clickable(
                                            onClick = { navigateToNotifications() },
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        )
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Notifications", fontSize = 14.sp
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                        contentDescription = "Arrow",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .size(22.dp)
                                            .clickable(
                                                onClick = { navigateToNotifications() },
                                                indication = null,
                                                interactionSource = remember { MutableInteractionSource() }
                                            )
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier
                                        .clickable(
                                            onClick = { navigateToLanguage() },
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        )
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Language", fontSize = 14.sp
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                        contentDescription = "Back Arrow",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .size(22.dp)
                                            .clickable(
                                                onClick = { navigateToLanguage() },
                                                indication = null,
                                                interactionSource = remember { MutableInteractionSource() }
                                            )
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(
                                            onClick = { scope.launch { appearanceSheetState.show() } },
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Appearance", fontSize = 14.sp
                                    )
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "Device settings  ", fontSize = 14.sp
                                        )
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                            contentDescription = "Back Arrow",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(22.dp)
                                                .clickable(
                                                    onClick = { scope.launch { appearanceSheetState.show() } },
                                                    indication = null,
                                                    interactionSource = remember { MutableInteractionSource() }
                                                )
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    // SECURITY
                    Column {
                        Text(
                            text = " SECURITY",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(
                                modifier = Modifier
                                    .height(320.dp)
                                    .background(color = Color.White)
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Security type", fontSize = 14.sp
                                    )
                                    Row(
                                        modifier = Modifier,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Authentication app   ", fontSize = 14.sp
                                        )
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                            contentDescription = "Back Arrow",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(22.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth()
                                        .clickable(
                                            onClick = { navigateToChangePasscode() },
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        ),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Change passcode", fontSize = 14.sp
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                        contentDescription = "Back Arrow",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .size(22.dp)
                                            .clickable(
                                                onClick = { navigateToChangePasscode() },
                                                indication = null,
                                                interactionSource = remember { MutableInteractionSource() }
                                            )
                                    )
                                }

                                SettingsItem(
                                    title = "Sign-in with biometrics",
                                    checked = false
                                )

                                SettingsItem(
                                    title = "Hide screen content on recent tasks/disable\n" +
                                            "screenshots",
                                    checked = false
                                )

                                Row(
                                    modifier = Modifier,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = "Hide balance with a flip gesture",
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = "Flip your device screen down to quickly hide and show\nbalances",
                                            fontSize = 13.sp, color = Color.Gray,
                                            lineHeight = 16.sp
                                        )
                                    }
                                    SettingsItem(
                                        title = "",
                                        checked = false
                                    )
                                }
                                Spacer(modifier = Modifier.height(20.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(
                                            onClick = { navigateToSecureAccount() },
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        ),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            Icons.AutoMirrored.Filled.ExitToApp,
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clickable(
                                                    onClick = { navigateToSecureAccount() },
                                                    indication = null,
                                                    interactionSource = remember { MutableInteractionSource() }
                                                )
                                        )
                                        Column {
                                            Text(
                                                text = " Secure my account", fontSize = 14.sp
                                            )
                                            Text(
                                                text = "  Log out from all devices except this one",
                                                fontSize = 12.sp,
                                                color = Color.Gray,
//                                                letterSpacing = (-0.5).sp,
//                                                lineHeight = 16.sp
                                            )
                                        }
                                    }
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                        contentDescription = "",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .size(22.dp)
                                            .clickable(
                                                onClick = { navigateToSecureAccount() },
                                                indication = null,
                                                interactionSource = remember { MutableInteractionSource() }
                                            )
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(color = Color.White)
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(
                                        onClick = { scope.launch { deleteAccountSheetState.show() } },
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(24.dp)
                                    )
                                    Text(
                                        text = "  Delete account", fontSize = 14.sp
                                    )
                                }
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                    contentDescription = "",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .size(22.dp)
                                        .clickable(
                                            onClick = { scope.launch { deleteAccountSheetState.show() } },
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        )
                                )
                            }
                        }
                    }

                    //////////////////////////////////////////////////////////SettingsDesign
                }
            }
        }
    }
}


@Composable
fun SettingsItem(
    title: String,
    checked: Boolean,
    modifier: Modifier = Modifier
) {
    var isChecked by remember { mutableStateOf(checked) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                uncheckedThumbColor = Color.DarkGray
            )
        )
    }
}


@Composable
fun AccountDeleteBottomSheet() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .height(260.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "How to delete your account", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "If you wish to permanently delete your Exness account, please reach out to support@exness.com. \n" +
                    "Tap the button below to read about the account termination process.",
            fontSize = 16.sp
        )

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(Color(0xFFE0E0E0))
        ) {
            Text(
                text = "Contact support", fontSize = 14.sp,
                color = Color.Black
            )
        }
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(Color(0xFFE0E0E0))
        ) {
            Text(
                text = "Termination conditions", fontSize = 14.sp,
                color = Color.Black
            )
        }
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFD54F))
        ) {
            Text(
                text = "Cancel", fontSize = 14.sp,
                color = Color.Black
            )
        }

    }
}


@Composable
fun AppearanceBottomSheet() {
    ChooseAppearanceScreen()
}

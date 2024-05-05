package com.github.hemoptysisheart.ui.navigation.compose

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator

@Composable
fun baseNavigator(
    activity: Activity = LocalContext.current as Activity
): BaseNavigator {
    val navController = rememberNavController()
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val baseNavigator = remember(activity) {
        BaseNavigator(
            activity = activity,
            navHostController = navController,
            softwareKeyboardController = softwareKeyboardController
        )
    }
    return baseNavigator
}

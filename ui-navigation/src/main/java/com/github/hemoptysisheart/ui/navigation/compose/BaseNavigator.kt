package com.github.hemoptysisheart.ui.navigation.compose

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import com.github.hemoptysisheart.ui.navigation.destination.Destination

@Composable
fun baseNavigator(
    activity: Activity = LocalContext.current as Activity,
    startDestination: Destination = object : Destination {
        override val id: String = "empty"
        override val arguments: List<NamedNavArgument> = emptyList()
        override val deepLinks: List<NavDeepLink> = emptyList()
        override fun route(vararg arguments: Any): String = id
    },
): BaseNavigator {
    val navController = rememberNavController()
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val baseNavigator = remember(activity) {
        BaseNavigator(
            activity = activity,
            navHostController = navController,
            startDestination = startDestination,
            softwareKeyboardController = softwareKeyboardController
        )
    }
    return baseNavigator
}

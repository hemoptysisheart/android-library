package com.github.hemoptysisheart.sample.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.navigation.NavigationGraph
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val softwareKeyboardController = LocalSoftwareKeyboardController.current
            val baseNavigator = remember {
                BaseNavigator(
                    activity = this,
                    navHostController = navController,
                    softwareKeyboardController = softwareKeyboardController
                )
            }

            AndroidLibraryTheme {
                NavigationGraph(baseNavigator)
            }
        }
    }
}

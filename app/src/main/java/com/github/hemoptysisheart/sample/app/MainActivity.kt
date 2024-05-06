package com.github.hemoptysisheart.sample.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.hemoptysisheart.sample.ui.navigation.NavigationGraph
import com.github.hemoptysisheart.sample.ui.navigation.SplashNavigator
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.ui.navigation.compose.baseNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidLibraryTheme {
                NavigationGraph(baseNavigator(this, SplashNavigator.Companion))
            }
        }
    }
}

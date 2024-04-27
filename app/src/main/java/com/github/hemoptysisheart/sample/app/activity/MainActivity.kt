package com.github.hemoptysisheart.sample.app.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.hemoptysisheart.sample.ui.navigation.NavigationGraph
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidLibraryTheme {
                NavigationGraph()
            }
        }
    }
}

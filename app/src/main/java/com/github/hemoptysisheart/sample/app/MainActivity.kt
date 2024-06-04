package com.github.hemoptysisheart.sample.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.github.hemoptysisheart.sample.ui.RootLayout
import com.github.hemoptysisheart.sample.ui.navigation.SplashNavigator
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.ui.navigation.compose.baseNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    init {
        Log.i(TAG, "#init called.")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "#onCreate args : savedInstanceState=$savedInstanceState")
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(
                LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current
            ) {
                AndroidLibraryTheme {
                    RootLayout(baseNavigator(this, SplashNavigator))
                }
            }
        }
    }

    override fun onStart() {
        Log.d(TAG, "#onStart called.")
        super.onStart()
    }

    override fun onResume() {
        Log.v(TAG, "#onResume called.")
        super.onResume()
    }

    override fun onPause() {
        Log.v(TAG, "#onPause called.")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "#onStop called.")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i(TAG, "#onDestroy called.")
        super.onDestroy()
    }
}

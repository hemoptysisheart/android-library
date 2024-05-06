package com.github.hemoptysisheart.sample.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.hemoptysisheart.sample.ui.navigation.NavigationGraphBuilder
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
            AndroidLibraryTheme {
                NavigationGraphBuilder(baseNavigator(this, SplashNavigator.Companion))
            }
        }
    }

    override fun onStart() {
        Log.i(TAG, "#onStart called.")
        super.onStart()
    }

    override fun onResume() {
        Log.i(TAG, "#onResume called.")
        super.onResume()
    }

    override fun onPause() {
        Log.i(TAG, "#onPause called.")
        super.onPause()
    }

    override fun onStop() {
        Log.i(TAG, "#onStop called.")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i(TAG, "#onDestroy called.")
        super.onDestroy()
    }
}

package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun SplashPage(navController: NavHostController) {
    Log.v(TAG, "#SplashPage called.")

    LaunchedEffect(Unit) {
        delay(5_000)
        navController.popBackStack()
        navController.navigate("input")
    }

    Text(text = "SplashPage")
}

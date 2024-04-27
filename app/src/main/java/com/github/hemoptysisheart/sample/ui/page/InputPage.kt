package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun InputPage(navController: NavHostController) {
    Log.v(TAG, "#InputPage called.")

    Text(text = "InputPage")
}

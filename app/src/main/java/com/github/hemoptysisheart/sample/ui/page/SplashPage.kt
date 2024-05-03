package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import kotlinx.coroutines.delay

@Composable
fun SplashPage(navController: NavHostController) {
    Log.v(TAG, "#SplashPage called.")

    LaunchedEffect(Unit) {
        delay(5_000)
        navController.popBackStack()
        navController.navigate("select-size")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "SplashPage")
    }
}

@Composable
@Preview(showSystemUi = true)
private fun SplashPagePreview() {
    AndroidLibraryTheme {
        SplashPage(rememberNavController())
    }
}
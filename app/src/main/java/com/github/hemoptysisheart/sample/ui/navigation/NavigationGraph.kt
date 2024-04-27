package com.github.hemoptysisheart.sample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.page.InputPage
import com.github.hemoptysisheart.sample.ui.page.SplashPage

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(startDestination = "splash", navController = navController) {
        composable("splash") {
            SplashPage(navController)
        }
        composable("input") {
            InputPage(navController)
        }
    }
}

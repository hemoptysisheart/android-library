package com.github.hemoptysisheart.sample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.hemoptysisheart.sample.ui.page.HistoryPage
import com.github.hemoptysisheart.sample.ui.page.MazePage
import com.github.hemoptysisheart.sample.ui.page.SelectSizePage
import com.github.hemoptysisheart.sample.ui.page.SplashPage
import com.github.hemoptysisheart.ui.navigation.compose.baseNavigator
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator

@Composable
fun NavigationGraph(
    baseNavigator: BaseNavigator = baseNavigator()
) {
    NavHost(startDestination = "splash", navController = baseNavigator.navHostController) {
        composable(SplashNavigator.id) {
            SplashPage(remember(SplashNavigator::class) { SplashNavigator(baseNavigator) })
        }
        composable(SelectSizeNavigator.id) {
            SelectSizePage(remember(SelectSizeNavigator::class) { SelectSizeNavigator(baseNavigator) })
        }
        composable(HistoryNavigator.id) {
            HistoryPage(remember(HistoryNavigator::class) { HistoryNavigator(baseNavigator) })
        }

        composable(MazeNavigator.id) {
            MazePage(remember(MazeNavigator::class) { MazeNavigator(baseNavigator) })
        }
    }
}

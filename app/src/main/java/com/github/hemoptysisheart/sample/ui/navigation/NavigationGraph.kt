package com.github.hemoptysisheart.sample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.hemoptysisheart.sample.ui.page.HistoryPage
import com.github.hemoptysisheart.sample.ui.page.MazePage
import com.github.hemoptysisheart.sample.ui.page.SelectSizePage
import com.github.hemoptysisheart.sample.ui.page.SplashPage
import com.github.hemoptysisheart.ui.navigation.compose.baseNavigator
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import com.github.hemoptysisheart.ui.navigation.destination.Navigator

@Composable
fun NavigationGraph(
    baseNavigator: BaseNavigator = baseNavigator()
) {
    NavHost(startDestination = baseNavigator.startDestination.id, navController = baseNavigator.navHostController) {
        node(SplashNavigator(baseNavigator)) {
            SplashPage(navigator = it)
        }
        node(SelectSizeNavigator(baseNavigator)) {
            SelectSizePage(navigator = it)
        }
        node(HistoryNavigator(baseNavigator)) {
            HistoryPage(navigator = it)
        }
        node(MazeNavigator(baseNavigator)) {
            MazePage(navigator = it)
        }
    }
}

private inline fun <reified N : Navigator> NavGraphBuilder.node(
    navigator: N,
    noinline content: @Composable (N) -> Unit
) {
    composable(navigator.destination.id) {
        content(navigator)
    }
}

package com.github.hemoptysisheart.sample.ui.navigation

import androidx.compose.runtime.Composable
import com.github.hemoptysisheart.sample.ui.page.HistoryPage
import com.github.hemoptysisheart.sample.ui.page.MazePage
import com.github.hemoptysisheart.sample.ui.page.SelectSizePage
import com.github.hemoptysisheart.sample.ui.page.SplashPage
import com.github.hemoptysisheart.ui.navigation.compose.NavigationGraph
import com.github.hemoptysisheart.ui.navigation.compose.baseNavigator
import com.github.hemoptysisheart.ui.navigation.compose.node
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator

@Composable
fun NavigationGraphBuilder(
    baseNavigator: BaseNavigator = baseNavigator()
) {
    NavigationGraph(baseNavigator = baseNavigator) {
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

package com.github.hemoptysisheart.sample.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.hemoptysisheart.sample.ui.page.HistoryPage
import com.github.hemoptysisheart.sample.ui.page.MazePage
import com.github.hemoptysisheart.sample.ui.page.SelectSizePage
import com.github.hemoptysisheart.sample.ui.page.SplashPage
import com.github.hemoptysisheart.statepump.ScaffoldPump
import com.github.hemoptysisheart.ui.navigation.compose.ScaffoldController
import com.github.hemoptysisheart.ui.navigation.compose.node
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator

/**
 * 앱의 전반적인 레이아웃을 구성한다.
 */
@Composable
fun GlobalLayout(
    baseNavigator: BaseNavigator,
    scaffoldPump: ScaffoldPump
) {
    ScaffoldController(
        baseNavigator = baseNavigator,
        scaffoldPump = scaffoldPump,
        modifier = Modifier.fillMaxSize()
    ) {
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

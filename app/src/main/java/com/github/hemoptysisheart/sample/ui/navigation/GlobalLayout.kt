package com.github.hemoptysisheart.sample.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.hemoptysisheart.sample.ui.page.HistoryPage
import com.github.hemoptysisheart.sample.ui.page.MazePage
import com.github.hemoptysisheart.sample.ui.page.SelectSizePage
import com.github.hemoptysisheart.sample.ui.page.SplashPage
import com.github.hemoptysisheart.statepump.ScaffoldPump
import com.github.hemoptysisheart.ui.compose.scaffold.BottomBarActions
import com.github.hemoptysisheart.ui.compose.scaffold.TopBarActions
import com.github.hemoptysisheart.ui.navigation.compose.NavigationGraph
import com.github.hemoptysisheart.ui.navigation.compose.Scaffold
import com.github.hemoptysisheart.ui.navigation.compose.page
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import com.github.hemoptysisheart.ui.state.scaffold.NavigationBarItemState

/**
 * 앱의 전반적인 레이아웃을 구성한다.
 */
@Composable
fun GlobalLayout(
    baseNavigator: BaseNavigator,
    scaffoldPump: ScaffoldPump
) {
    val topBar by scaffoldPump.topBar.collectAsStateWithLifecycle()
    val bottomBar by scaffoldPump.bottomBar.collectAsStateWithLifecycle()

    val topBarActions = remember(baseNavigator) {
        object : TopBarActions {
            override fun onClickBack() = baseNavigator.back()
        }
    }
    val bottomBarActions = remember(scaffoldPump) {
        object : BottomBarActions {
            override fun onClickNavigationBarItem(item: NavigationBarItemState) {
                baseNavigator.navHostController.navigate(item.destination) {
                    popUpTo(baseNavigator.startDestination.id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }

    Scaffold(
        baseNavigator = baseNavigator,
        topBar = topBar,
        bottomBar = bottomBar,
        modifier = Modifier.fillMaxSize(),
        topBarActions = topBarActions,
        bottomBarActions = bottomBarActions
    ) {
        NavigationGraph(
            navHostController = baseNavigator.navHostController,
            startDestinationId = baseNavigator.startDestination.id
        ) {
            page(SplashNavigator(baseNavigator)) {
                SplashPage(navigator = it)
            }
            page(SelectSizeNavigator(baseNavigator)) {
                SelectSizePage(navigator = it)
            }
            page(HistoryNavigator(baseNavigator)) {
                HistoryPage(navigator = it)
            }
            page(MazeNavigator(baseNavigator)) {
                MazePage(navigator = it)
            }
        }
    }
}

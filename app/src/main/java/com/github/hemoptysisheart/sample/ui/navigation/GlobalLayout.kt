package com.github.hemoptysisheart.sample.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.hemoptysisheart.sample.ui.page.HistoryPage
import com.github.hemoptysisheart.sample.ui.page.MazePage
import com.github.hemoptysisheart.sample.ui.page.SelectSizePage
import com.github.hemoptysisheart.sample.ui.page.SplashPage
import com.github.hemoptysisheart.ui.compose.scaffold.BottomBar
import com.github.hemoptysisheart.ui.compose.scaffold.BottomBarActions
import com.github.hemoptysisheart.ui.compose.scaffold.TopBar
import com.github.hemoptysisheart.ui.compose.scaffold.TopBarActions
import com.github.hemoptysisheart.ui.navigation.compose.NavigationGraph
import com.github.hemoptysisheart.ui.navigation.compose.Scaffold
import com.github.hemoptysisheart.ui.navigation.compose.page
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import com.github.hemoptysisheart.ui.navigation.viewmodel.ScaffoldControlViewModel
import com.github.hemoptysisheart.ui.state.scaffold.NavigationBarItemState

/**
 * 앱의 전반적인 레이아웃을 구성한다.
 */
@Composable
fun GlobalLayout(
    baseNavigator: BaseNavigator,
    viewModel: ScaffoldControlViewModel = hiltViewModel()
) {
    Log.v(
        TAG,
        listOf(
            "baseNavigator=$baseNavigator",
            "viewModel=$viewModel"
        ).joinToString(", ", "#GlobalLayout args : ")
    )

    val dialog by viewModel.dialog.collectAsStateWithLifecycle()
    val visibleProgress by viewModel.visibleProgress.collectAsStateWithLifecycle()
    val topBar by viewModel.topBar.collectAsStateWithLifecycle()
    val bottomBar by viewModel.bottomBar.collectAsStateWithLifecycle()

    val topBarActions = remember(viewModel) {
        object : TopBarActions {
            override fun onClickBack() = baseNavigator.back()
        }
    }
    val bottomBarActions = remember(viewModel) {
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
        dialog = dialog,
        visibleProgress = visibleProgress,
        topBar = {
            TopBar(state = topBar, modifier = Modifier.fillMaxWidth(), actions = topBarActions)
        },
        bottomBar = {
            BottomBar(state = bottomBar, modifier = Modifier.fillMaxWidth(), actions = bottomBarActions)
        },
        modifier = Modifier.fillMaxSize()
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

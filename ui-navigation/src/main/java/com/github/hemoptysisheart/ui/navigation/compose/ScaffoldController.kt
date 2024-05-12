package com.github.hemoptysisheart.ui.navigation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.hemoptysisheart.ui.compose.scaffold.BottomBar
import com.github.hemoptysisheart.ui.compose.scaffold.BottomBarActions
import com.github.hemoptysisheart.ui.compose.scaffold.TopBar
import com.github.hemoptysisheart.ui.compose.scaffold.TopBarActions
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import com.github.hemoptysisheart.ui.state.scaffold.BottomBarState
import com.github.hemoptysisheart.ui.state.scaffold.TopBarState

/**
 * 전체적으로 스캐폴드 레이아웃을 제어하는 컴포저블.
 */
@Composable
fun ScaffoldController(
    baseNavigator: BaseNavigator,
    modifier: Modifier = Modifier,
    topBar: TopBarState? = null,
    bottomBar: BottomBarState? = null,
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    topBarActions: TopBarActions = TopBarActions.Default,
    bottomBarActions: BottomBarActions = BottomBarActions.Default,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                state = topBar,
                modifier = Modifier.fillMaxWidth(),
                actions = topBarActions
            )
        },
        bottomBar = {
            BottomBar(
                state = bottomBar,
                modifier = Modifier.fillMaxWidth(),
                actions = bottomBarActions
            )
        },
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            content()
        }
    }
}

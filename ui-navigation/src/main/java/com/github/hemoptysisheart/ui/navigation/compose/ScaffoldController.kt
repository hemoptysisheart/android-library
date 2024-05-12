package com.github.hemoptysisheart.ui.navigation.compose

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.github.hemoptysisheart.statepump.ScaffoldPump
import com.github.hemoptysisheart.ui.compose.scaffold.BottomBar
import com.github.hemoptysisheart.ui.compose.scaffold.BottomBarActions
import com.github.hemoptysisheart.ui.compose.scaffold.TopBar
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator

/**
 * 전체적으로 스캐폴드 레이아웃을 제어하는 컴포저블.
 */
@Composable
fun ScaffoldController(
    baseNavigator: BaseNavigator,
    scaffoldPump: ScaffoldPump,
    modifier: Modifier = Modifier,
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    bottomBarActions: BottomBarActions = BottomBarActions.Default,
    graphBuilder: NavGraphBuilder.() -> Unit
) {
    val topBar by scaffoldPump.topBar.collectAsStateWithLifecycle()
    val bottomBar by scaffoldPump.bottomBar.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = { TopBar(topBar) },
        bottomBar = { BottomBar(state = bottomBar, modifier = Modifier.fillMaxWidth(), actions = bottomBarActions) },
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets
    ) { padding ->
        NavigationGraph(
            navHostController = baseNavigator.navHostController,
            startDestinationId = baseNavigator.startDestination.id,
            modifier = Modifier.padding(padding),
            graphBuilder = graphBuilder
        )
    }
}

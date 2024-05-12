package com.github.hemoptysisheart.ui.navigation.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.github.hemoptysisheart.statepump.ScaffoldPump
import com.github.hemoptysisheart.ui.compose.scaffold.BottomBar
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
    graphBuilder: NavGraphBuilder.(BaseNavigator) -> Unit
) {
    val topBar by scaffoldPump.topBar.collectAsStateWithLifecycle()
    val bottomBar by scaffoldPump.bottomBar.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = { TopBar(state = topBar, modifier = Modifier.fillMaxWidth()) },
        bottomBar = { BottomBar(state = bottomBar, modifier = Modifier.fillMaxWidth()) }
    ) { padding ->
        NavigationGraph(
            baseNavigator = baseNavigator,
            modifier = Modifier.padding(padding),
            graphBuilder = graphBuilder
        )
    }
}

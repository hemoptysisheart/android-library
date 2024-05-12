package com.github.hemoptysisheart.ui.navigation.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.github.hemoptysisheart.ui.compose.scaffold.BottomBar
import com.github.hemoptysisheart.ui.compose.scaffold.TopBar
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import com.github.hemoptysisheart.viewmodel.ScaffoldControllerViewModel

/**
 * 전체적으로 스캐폴드 레이아웃을 제어하는 컴포저블.
 */
@Composable
fun ScaffoldController(
    baseNavigator: BaseNavigator,
    modifier: Modifier = Modifier,
    graphBuilder: NavGraphBuilder.(BaseNavigator) -> Unit
) {
    val viewModel = remember {
        ScaffoldControllerViewModel(
            scaffoldPump = TODO("fallbackCoroutineExceptionHandler 컴포넌트를 어떻게 가져올 것인가?")
        )
    }

    val topBar by viewModel.topBar.collectAsStateWithLifecycle()
    val bottomBar by viewModel.bottomBar.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = { TopBar(state = topBar) },
        bottomBar = { BottomBar(state = bottomBar) }
    ) { padding ->
        NavigationGraph(
            baseNavigator = baseNavigator,
            modifier = Modifier.padding(padding),
            graphBuilder = graphBuilder
        )
    }
}

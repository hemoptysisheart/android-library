package com.github.hemoptysisheart.ui.navigation.compose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import com.github.hemoptysisheart.ui.navigation.destination.Navigator

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    startDestinationId: String,
    modifier: Modifier = Modifier,
    graphBuilder: NavGraphBuilder.() -> Unit
) {
    Log.v(
        TAG,
        listOf(
            "navHostController=$navHostController",
            "startDestinationId=$startDestinationId",
            "modifier=$modifier"
        ).joinToString(", ", "#NavigationGraph args : ")
    )

    NavHost(
        navController = navHostController,
        startDestination = startDestinationId,
        modifier = modifier
    ) {
        graphBuilder()
    }
}

/**
 * 내비게이션 대상(`destination`)을 추가합니다.
 *
 * @param navigator 화면에서 사용할 내비게이터.
 * @param destination 화면.
 */
inline fun <reified N : Navigator> NavGraphBuilder.page(navigator: N, noinline destination: @Composable (N) -> Unit) {
    if (navigator is BaseNavigator) {
        throw IllegalArgumentException("BaseNavigator is not allowed.")
    }

    composable(navigator.destination.id) {
        destination(navigator)
    }
}

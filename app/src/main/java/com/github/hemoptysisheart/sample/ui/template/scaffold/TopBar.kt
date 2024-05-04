package com.github.hemoptysisheart.sample.ui.template.scaffold

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.hemoptysisheart.ui.state.SimpleTopBarState
import com.github.hemoptysisheart.ui.state.TopBarState

@Composable
fun TopBar(navController: NavHostController, state: TopBarState, modifier: Modifier = Modifier) {
    Log.v(TAG, "#TopBar args : navController=$navController, state=$state")

    when (state) {
        is SimpleTopBarState ->
            SimpleTopBar(navController, state, modifier)

        else ->
            throw IllegalArgumentException("unsupported state type : state=$state, state::class=${state::class.qualifiedName}")
    }
}

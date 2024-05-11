package com.github.hemoptysisheart.ui.compose.scaffold

import androidx.compose.runtime.Composable
import com.github.hemoptysisheart.ui.state.scaffold.SimpleTopBarState
import com.github.hemoptysisheart.ui.state.scaffold.TitleTopBarState
import com.github.hemoptysisheart.ui.state.scaffold.TopBarState

@Composable
fun TopBar(state: TopBarState) {
    when (state) {
        is TitleTopBarState ->
            TitleTopBar(state)

        is SimpleTopBarState ->
            SimpleTopBar(state)

        else ->
            throw IllegalArgumentException("unsupported state type : state=$state, state::class=${state::class.qualifiedName}")
    }
}

package com.github.hemoptysisheart.ui.compose.scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.hemoptysisheart.ui.state.scaffold.SimpleTopBarState
import com.github.hemoptysisheart.ui.state.scaffold.TitleTopBarState
import com.github.hemoptysisheart.ui.state.scaffold.TopBarState

@Composable
fun TopBar(state: TopBarState?, modifier: Modifier = Modifier) {
    when (state) {
        null -> {
            // No TopBar
        }

        is TitleTopBarState -> TitleTopBar(state, modifier)

        is SimpleTopBarState -> SimpleTopBar(state, modifier)

        else -> throw IllegalArgumentException(
            "unsupported state type : state=$state, state::class=${state::class.qualifiedName}"
        )
    }
}

package com.github.hemoptysisheart.ui.compose.scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.hemoptysisheart.ui.state.scaffold.SimpleTopBarState
import com.github.hemoptysisheart.ui.state.scaffold.TitleTopBarState
import com.github.hemoptysisheart.ui.state.scaffold.TopBarState

@Composable
fun TopBar(
    state: TopBarState?,
    modifier: Modifier = Modifier,
    actions: TopBarActions = TopBarActions.Default
) {
    when (state) {
        null -> {
            // No TopBar
        }

        is TitleTopBarState ->
            TitleTopBar(state = state, modifier = modifier)

        is SimpleTopBarState ->
            SimpleTopBar(state = state, modifier = modifier, onClickBack = actions::onClickBack)

        else -> throw IllegalArgumentException(
            "unsupported state type : state=$state, state::class=${state::class.qualifiedName}"
        )
    }
}

interface TopBarActions {
    companion object {
        val Default = object : TopBarActions {
            override fun onClickBack() {}
        }
    }

    fun onClickBack()
}
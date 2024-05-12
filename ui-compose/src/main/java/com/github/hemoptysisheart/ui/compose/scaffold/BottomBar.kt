package com.github.hemoptysisheart.ui.compose.scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.hemoptysisheart.ui.state.scaffold.BottomBarState
import com.github.hemoptysisheart.ui.state.scaffold.NavigationBarItemState
import com.github.hemoptysisheart.ui.state.scaffold.NavigationBarState

@Composable
fun BottomBar(
    state: BottomBarState?,
    modifier: Modifier = Modifier,
    actions: BottomBarActions = BottomBarActions.Default
) {
    when (state) {
        null -> {
            // Do nothing
        }

        is NavigationBarState ->
            NavigationBar(
                state = state,
                modifier = modifier,
                onClickItem = actions::onClickNavigationBarItem
            )

        else ->
            throw IllegalArgumentException("Unsupported state type : state=$state, state::class=${state::class}")
    }
}

interface BottomBarActions {
    companion object {
        val Default = object : BottomBarActions {
            override fun onClickNavigationBarItem(item: NavigationBarItemState) {}
        }
    }

    fun onClickNavigationBarItem(item: NavigationBarItemState)
}

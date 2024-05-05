package com.github.hemoptysisheart.sample.ui.template.scaffold

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.hemoptysisheart.ui.navigation.destination.Navigator
import com.github.hemoptysisheart.ui.state.SimpleTopBarState
import com.github.hemoptysisheart.ui.state.TopBarState

@Composable
fun TopBar(navigator: Navigator, state: TopBarState, modifier: Modifier = Modifier) {
    Log.v(TAG, "#TopBar args : navigator=$navigator, state=$state")

    when (state) {
        is SimpleTopBarState ->
            SimpleTopBar(navigator, state, modifier)

        else ->
            throw IllegalArgumentException("unsupported state type : state=$state, state::class=${state::class.qualifiedName}")
    }
}

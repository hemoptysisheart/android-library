package com.github.hemoptysisheart.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import com.github.hemoptysisheart.ui.state.IconState

@Composable
fun Icon(state: IconState, modifier: Modifier = Modifier) {
    state.resourceId?.let {
        androidx.compose.material3.Icon(
            painter = painterResource(it),
            contentDescription = state.contentDescription,
            tint = state.tint,
            modifier = modifier.testTag(state.testTag)
        )
    }
    state.imageVector?.let {
        androidx.compose.material3.Icon(
            imageVector = it,
            contentDescription = state.contentDescription,
            tint = state.tint,
            modifier = modifier.testTag(state.testTag)
        )
    }
    state.bitmap?.let {
        androidx.compose.material3.Icon(
            bitmap = it,
            contentDescription = state.contentDescription,
            tint = state.tint,
            modifier = modifier.testTag(state.testTag)
        )
    }
    state.painter?.let {
        androidx.compose.material3.Icon(
            painter = it,
            contentDescription = state.contentDescription,
            tint = state.tint,
            modifier = modifier.testTag(state.testTag)
        )
    }
}

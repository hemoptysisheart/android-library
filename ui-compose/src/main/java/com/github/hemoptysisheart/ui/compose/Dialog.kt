package com.github.hemoptysisheart.ui.compose

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.github.hemoptysisheart.ui.state.DialogState
import com.github.hemoptysisheart.ui.state.IndeterminateProgressDialogState

@Composable
fun Dialog(state: DialogState, onDismissRequest: () -> Unit = {}) {
    when (state) {
        is IndeterminateProgressDialogState ->
            androidx.compose.ui.window.Dialog(
                onDismissRequest = onDismissRequest,
                properties = state.properties
            ) {
                CircularProgressIndicator(modifier = Modifier.testTag(state.testTag))
            }

        else ->
            throw IllegalArgumentException("unsupported state : state=$state, state::class=${state::class}")
    }
}

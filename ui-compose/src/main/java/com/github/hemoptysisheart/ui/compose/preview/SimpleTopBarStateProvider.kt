package com.github.hemoptysisheart.ui.compose.preview

import android.annotation.SuppressLint
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.core.R
import com.github.hemoptysisheart.ui.state.scaffold.SimpleTopBarState

@Deprecated("샘플 코드.")
class SimpleTopBarStateProvider : PreviewParameterProvider<SimpleTopBarState> {
    @SuppressLint("PrivateResource")
    override val values = sequenceOf(
        SimpleTopBarState(
            enableBack = true,
            title = "backward enable"
        ),
        SimpleTopBarState(
            enableBack = false,
            title = "backward disable"
        ),
        SimpleTopBarState(
            enableBack = true,
            title = "backward enable, leading icon",
            leadingIcon = R.drawable.ic_call_decline
        ),
        SimpleTopBarState(
            enableBack = false,
            title = "backward disable, leading icon",
            leadingIcon = R.drawable.ic_call_decline
        ),
        SimpleTopBarState(
            enableBack = true,
            title = "backward enable, trailing icon",
            trailingIcon = R.drawable.ic_call_answer
        ),
        SimpleTopBarState(
            enableBack = false,
            title = "backward disable, trailing icon",
            trailingIcon = R.drawable.ic_call_answer
        ),
        SimpleTopBarState(
            enableBack = true,
            title = "back enable, leading, trailing",
            leadingIcon = R.drawable.ic_call_decline,
            trailingIcon = R.drawable.ic_call_answer
        ),
        SimpleTopBarState(
            enableBack = false,
            title = "back disable, leading, trailing",
            leadingIcon = R.drawable.ic_call_decline,
            trailingIcon = R.drawable.ic_call_answer
        ),
    )
}

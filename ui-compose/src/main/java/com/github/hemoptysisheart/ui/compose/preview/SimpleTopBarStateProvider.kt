package com.github.hemoptysisheart.ui.compose.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.github.hemoptysisheart.ui.state.SimpleTopBarState

class SimpleTopBarStateProvider : PreviewParameterProvider<SimpleTopBarState> {
    override val values = sequenceOf(
        SimpleTopBarState(
            enableBackward = true,
            title = "backward enable"
        ),
        SimpleTopBarState(
            enableBackward = false,
            title = "backward disable"
        ),
        SimpleTopBarState(
            enableBackward = true,
            title = "backward enable, leading icon",
            leadingIcon = android.R.drawable.ic_media_next
        ),
        SimpleTopBarState(
            enableBackward = false,
            title = "backward disable, leading icon",
            leadingIcon = android.R.drawable.ic_media_next
        ),
        SimpleTopBarState(
            enableBackward = true,
            title = "backward enable, trailing icon",
            trailingIcon = android.R.drawable.ic_delete
        ),
        SimpleTopBarState(
            enableBackward = false,
            title = "backward disable, trailing icon",
            trailingIcon = android.R.drawable.ic_delete
        ),
        SimpleTopBarState(
            enableBackward = true,
            title = "back enable, leading, trailing",
            leadingIcon = android.R.drawable.ic_media_next,
            trailingIcon = android.R.drawable.ic_delete
        ),
        SimpleTopBarState(
            enableBackward = false,
            title = "back disable, leading, trailing",
            leadingIcon = android.R.drawable.ic_media_next,
            trailingIcon = android.R.drawable.ic_delete
        ),
    )
}

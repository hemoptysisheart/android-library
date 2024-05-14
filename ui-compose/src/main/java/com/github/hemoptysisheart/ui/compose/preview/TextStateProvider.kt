package com.github.hemoptysisheart.ui.compose.preview

import androidx.compose.material3.R
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.github.hemoptysisheart.ui.state.TextLines
import com.github.hemoptysisheart.ui.state.TextState

class TextStateProvider : PreviewParameterProvider<TextState> {
    override val values: Sequence<TextState> = sequenceOf(
        TextState(text = ""),
        TextState(text = "Hello, World!"),
        TextState(text = "Hello\nWorld!", textLines = TextLines.Default),
        TextState(resourceId = R.string.m3c_bottom_sheet_pane_title),
    )
}

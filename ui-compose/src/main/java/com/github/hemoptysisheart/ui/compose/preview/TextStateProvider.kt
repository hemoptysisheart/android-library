package com.github.hemoptysisheart.ui.compose.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.github.hemoptysisheart.ui.state.TextLines
import com.github.hemoptysisheart.ui.state.TextState

class TextStateProvider : PreviewParameterProvider<TextState> {
    override val values: Sequence<TextState> = sequenceOf(
        TextState(""),
        TextState("Hello, World!"),
        TextState("Hello\nWorld!", textLines = TextLines.Default)
    )
}

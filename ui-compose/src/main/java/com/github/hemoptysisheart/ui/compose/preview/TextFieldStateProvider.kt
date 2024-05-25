package com.github.hemoptysisheart.ui.compose.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.github.hemoptysisheart.ui.state.MultiLines
import com.github.hemoptysisheart.ui.state.SimpleTextFieldState
import com.github.hemoptysisheart.ui.state.TextFieldState

class TextFieldStateProvider : PreviewParameterProvider<TextFieldState> {
    override val values = listOf(
        SimpleTextFieldState(text = "", onValueChange = { _, _ -> }),
        SimpleTextFieldState(text = "with value", onValueChange = { _, _ -> }),
        SimpleTextFieldState(text = "disabled", enabled = false, onValueChange = { _, _ -> }),
        SimpleTextFieldState(text = "read only", readOnly = true, onValueChange = { _, _ -> }),
        SimpleTextFieldState(text = "with error", isError = true, onValueChange = { _, _ -> }),
        SimpleTextFieldState(text = "multiple\nline\nfield", lines = MultiLines(4), onValueChange = { _, _ -> })
    ).asSequence()
}

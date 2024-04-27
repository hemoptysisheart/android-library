package com.github.hemoptysisheart.ui.compose.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.github.hemoptysisheart.ui.state.MultiLines
import com.github.hemoptysisheart.ui.state.SimpleTextFieldState
import com.github.hemoptysisheart.ui.state.TextFieldState

class TextFieldStateProvider : PreviewParameterProvider<TextFieldState> {
    override val values = listOf(
        SimpleTextFieldState(text = "", onValueChange = { }),
        SimpleTextFieldState(text = "with value", onValueChange = { }),
        SimpleTextFieldState(text = "disabled", enabled = false, onValueChange = { }),
        SimpleTextFieldState(text = "read only", readOnly = true, onValueChange = { }),
        SimpleTextFieldState(text = "with error", isError = true, onValueChange = { }),
        SimpleTextFieldState(text = "multiple\nline\nfield", lines = MultiLines(4), onValueChange = { })
    ).asSequence()
}

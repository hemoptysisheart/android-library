package com.github.hemoptysisheart.ui.compose

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.github.hemoptysisheart.ui.compose.preview.TextStateProvider
import com.github.hemoptysisheart.ui.state.MultiLines
import com.github.hemoptysisheart.ui.state.SingleLine
import com.github.hemoptysisheart.ui.state.TextLines
import com.github.hemoptysisheart.ui.state.TextState

@Composable
fun Text(
    state: TextState,
    modifier: Modifier = Modifier,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    androidx.compose.material3.Text(
        text = state.resourceId?.let { stringResource(id = it) }
            ?: state.text
            ?: throw IllegalStateException("TextState must have resourceId or text."),
        modifier = modifier.testTag(state.testTag),
        color = state.color,
        fontSize = state.fontSize,
        fontStyle = state.fontStyle,
        fontWeight = state.fontWeight,
        fontFamily = state.fontFamily,
        letterSpacing = state.letterSpacing,
        textDecoration = state.textDecoration,
        textAlign = state.textAlign,
        lineHeight = state.lineHeight,
        overflow = state.overflow,
        softWrap = state.softWrap,
        maxLines = state.textLines.let {
            when (it) {
                is TextLines.Default -> it.maxLines
                is SingleLine -> 1
                is MultiLines -> it.maxLines
            }
        },
        minLines = state.textLines.let {
            when (it) {
                is TextLines.Default -> it.minLines
                is SingleLine -> 1
                is MultiLines -> it.minLines
            }
        },
        onTextLayout = onTextLayout,
        style = state.style ?: LocalTextStyle.current
    )
}

@Composable
@Preview(showBackground = true)
private fun TextPreview(@PreviewParameter(TextStateProvider::class) state: TextState) {
    Text(state)
}

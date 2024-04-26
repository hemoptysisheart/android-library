package com.github.hemoptysisheart.ui.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.github.hemoptysisheart.ui.state.SimpleTextFieldState
import com.github.hemoptysisheart.ui.state.TextFieldState

/**
 * 텍스트 필드.
 *
 * @param state 텍스트 필드의 상태.
 * @param modifier 컴포저의 Modifier.
 * @param textStyle 텍스트 스타일.
 * @param label 레이블.
 * @param placeholder 플레이스홀더.
 * @param leadingIcon 선행 아이콘.
 * @param trailingIcon 후행 아이콘.
 * @param prefix 접두사.
 * @param suffix 접미사.
 * @param supportingText 지원 텍스트.
 * @param interactionSource 상호작용 소스.
 * @param shape 모양.
 * @param colors 색상.
 */
@Composable
fun TextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors()
) {
    androidx.compose.material3.TextField(
        value = state.value,
        onValueChange = state::onChange,
        modifier = modifier,
        enabled = state.enabled,
        readOnly = state.readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = state.isError,
        visualTransformation = state.visualTransformation,
        keyboardOptions = state.keyboardOptions,
        keyboardActions = state.keyboardActions,
        minLines = state.minLines,
        maxLines = state.maxLines,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors
    )
}

private class TextFieldStateProvider : PreviewParameterProvider<TextFieldState> {
    override val values = listOf(
        SimpleTextFieldState(text = "", onChange = { }),
        SimpleTextFieldState(text = "with value", onChange = { }),
        SimpleTextFieldState(text = "disabled", enabled = false, onChange = { }),
        SimpleTextFieldState(text = "read only", readOnly = true, onChange = { }),
        SimpleTextFieldState(text = "with error", isError = true, onChange = { })
    ).asSequence()
}

@Composable
@Preview(showBackground = true)
fun TextFieldPreview(@PreviewParameter(TextFieldStateProvider::class) state: TextFieldState) {
    TextField(state = state)
}

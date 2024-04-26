package com.github.hemoptysisheart.ui.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.github.hemoptysisheart.ui.compose.preview.TextFieldStateProvider
import com.github.hemoptysisheart.ui.state.MultiLines
import com.github.hemoptysisheart.ui.state.SingleLine
import com.github.hemoptysisheart.ui.state.TextFieldState
import com.github.hemoptysisheart.ui.state.TextLines

/**
 * 외곽선이 있는 텍스트 필드.
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
fun OutlinedTextField(
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
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    val lines = state.lines
    androidx.compose.material3.OutlinedTextField(
        value = state.value,
        onValueChange = state::onValueChange,
        modifier = modifier
            .testTag("${state.key}")
            .onFocusChanged { state.onFocusedChange(it) },
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
        singleLine = state.lines is SingleLine,
        minLines = when (lines) {
            is TextLines.Default -> lines.minLines
            is SingleLine -> 1
            is MultiLines -> lines.minLines
        },
        maxLines = when (lines) {
            is TextLines.Default -> lines.maxLines
            is SingleLine -> 1
            is MultiLines -> lines.maxLines
        },
        interactionSource = interactionSource,
        shape = shape,
        colors = colors
    )
}

@Composable
@Preview(showBackground = true)
fun OutlinedTextFieldPreview(@PreviewParameter(TextFieldStateProvider::class) state: TextFieldState) {
    Surface(modifier = Modifier.padding(10.dp)) {
        OutlinedTextField(state = state)
    }
}

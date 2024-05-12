package com.github.hemoptysisheart.ui.state

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Stable
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import java.util.UUID

/**
 * @param value 초기값.
 */
@Stable
open class ParsableTextFieldState<V>(
    value: TextFieldValue,
    key: UUID = UUID.randomUUID(),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    lines: TextLines = TextLines.Default,
    onFocusChange: (FocusState) -> Unit = { },
    onValueChange: (TextFieldValue) -> Unit,
    protected val _parser: (String) -> V,
) : SimpleTextFieldState(
    value = value,
    key = key,
    enabled = enabled,
    readOnly = readOnly,
    isError = isError,
    visualTransformation = visualTransformation,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    lines = lines,
    _onFocusChange = onFocusChange,
    _onValueChange = onValueChange
) {
    constructor(
        value: V,
        key: UUID = UUID.randomUUID(),
        enabled: Boolean = true,
        readOnly: Boolean = false,
        isError: Boolean = false,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        lines: TextLines = TextLines.Default,
        onFocusChange: (FocusState) -> Unit = { },
        onValueChange: (TextFieldValue) -> Unit,
        parser: (String) -> V
    ) : this(
        value = TextFieldValue("$value", TextRange("$value".length)),
        key = key,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        lines = lines,
        onFocusChange = onFocusChange,
        onValueChange = onValueChange,
        _parser = parser
    )

    fun parse(): V = _parser(value.text)

    override fun copy(
        value: TextFieldValue,
        enabled: Boolean,
        readOnly: Boolean,
        isError: Boolean,
        visualTransformation: VisualTransformation,
        keyboardOptions: KeyboardOptions,
        keyboardActions: KeyboardActions,
        lines: TextLines
    ) = ParsableTextFieldState(
        value = value,
        key = key,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        lines = lines,
        onFocusChange = _onFocusChange,
        onValueChange = _onValueChange,
        _parser = _parser
    )

    override fun copy(
        text: String,
        enabled: Boolean,
        readOnly: Boolean,
        isError: Boolean,
        visualTransformation: VisualTransformation,
        keyboardOptions: KeyboardOptions,
        keyboardActions: KeyboardActions,
        lines: TextLines
    ) = ParsableTextFieldState(
        value = TextFieldValue(text, TextRange(text.length)),
        key = key,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        lines = lines,
        onFocusChange = _onFocusChange,
        onValueChange = _onValueChange,
        _parser = _parser
    )
}

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
 * 콜백을 주입할 수 있는 텍스트 필드의 상태.
 *
 * 상태를 추적하기 쉽게 하기 위해 주입한 콜백은 [copy]로 교체할 수 없다. 경우에 따라 서로 다른 콜백 로직이 필요하다면, 주입한 콜백 자체가 선택적으로
 * 로직을 실행할 수 있도록 해야 한다.
 */
@Stable
open class SimpleTextFieldState(
    override val value: TextFieldValue,
    override val key: UUID = UUID.randomUUID(),
    override val enabled: Boolean = true,
    override val readOnly: Boolean = false,
    override val isError: Boolean = false,
    override val visualTransformation: VisualTransformation = VisualTransformation.None,
    override val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    override val keyboardActions: KeyboardActions = KeyboardActions.Default,
    override val lines: TextLines = TextLines.Default,
    private val _onFocusChange: (FocusState) -> Unit = { },
    private val _onValueChange: (TextFieldValue) -> Unit
) : TextFieldState {
    constructor(
        text: String,
        key: UUID = UUID.randomUUID(),
        enabled: Boolean = true,
        readOnly: Boolean = false,
        isError: Boolean = false,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        lines: TextLines = TextLines.Default,
        onFocusChange: (FocusState) -> Unit = { },
        onValueChange: (TextFieldValue) -> Unit
    ) : this(
        TextFieldValue(text = text, selection = TextRange(text.length)),
        key,
        enabled,
        readOnly,
        isError,
        visualTransformation,
        keyboardOptions,
        keyboardActions,
        lines,
        onFocusChange,
        onValueChange
    )

    override fun onFocusedChange(focusState: FocusState) {
        _onFocusChange(focusState)
    }

    final override fun onValueChange(value: TextFieldValue) {
        _onValueChange(value)
    }

    override fun copy(
        value: TextFieldValue,
        enabled: Boolean,
        readOnly: Boolean,
        isError: Boolean,
        visualTransformation: VisualTransformation,
        keyboardOptions: KeyboardOptions,
        keyboardActions: KeyboardActions,
        lines: TextLines
    ) = SimpleTextFieldState(
        value = value,
        key = key,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        lines = lines,
        _onFocusChange = _onFocusChange,
        _onValueChange = _onValueChange
    )

    override fun equals(other: Any?) = other === this || (
            other is SimpleTextFieldState &&
                    other.value == value &&
                    other.enabled == enabled &&
                    other.readOnly == readOnly &&
                    other.isError == isError &&
                    other.visualTransformation == visualTransformation &&
                    other.keyboardOptions == keyboardOptions &&
                    other.keyboardActions == keyboardActions &&
                    other.lines == lines &&
                    other._onFocusChange == _onFocusChange &&
                    other._onValueChange == _onValueChange
            )


    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + enabled.hashCode()
        result = 31 * result + readOnly.hashCode()
        result = 31 * result + isError.hashCode()
        result = 31 * result + visualTransformation.hashCode()
        result = 31 * result + keyboardOptions.hashCode()
        result = 31 * result + keyboardActions.hashCode()
        result = 31 * result + lines.hashCode()
        result = 31 * result + _onFocusChange.hashCode()
        result = 31 * result + _onValueChange.hashCode()
        return result
    }

    override fun toString() = listOf(
        "value=$value",
        "enabled=$enabled",
        "readOnly=$readOnly",
        "isError=$isError",
        "visualTransformation=$visualTransformation",
        "keyboardOptions=$keyboardOptions",
        "keyboardActions=$keyboardActions",
        "lines=$lines",
        "_onFocusChange=$_onFocusChange",
        "_onChange=$_onValueChange"
    ).joinToString(", ")
}
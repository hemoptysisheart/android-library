package com.github.hemoptysisheart.ui.state

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

/**
 * 콜백을 주입할 수 있는 텍스트 필드의 상태.
 *
 * 상태를 추적하기 쉽게 하기 위해 주입한 콜백은 [copy]로 교체할 수 없다. 경우에 따라 서로 다른 콜백 로직이 필요하다면, 주입한 콜백 자체가 선택적으로
 * 로직을 실행할 수 있도록 해야 한다.
 *
 * @param _onChange 외부에서 주입한 텍스트 필드의 값 변경 콜백.
 */
open class SimpleTextFieldState(
    override val value: TextFieldValue,
    override val enabled: Boolean = true,
    override val readOnly: Boolean = false,
    override val isError: Boolean = false,
    override val visualTransformation: VisualTransformation = VisualTransformation.None,
    override val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    override val keyboardActions: KeyboardActions = KeyboardActions.Default,
    override val minLines: Int = 1,
    override val maxLines: Int = Int.MAX_VALUE,
    private val _onChange: (TextFieldValue) -> Unit
) : TextFieldState {
    constructor(
        text: String,
        enabled: Boolean = true,
        readOnly: Boolean = false,
        isError: Boolean = false,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
        onChange: (TextFieldValue) -> Unit
    ) : this(
        TextFieldValue(text),
        enabled,
        readOnly,
        isError,
        visualTransformation,
        keyboardOptions,
        keyboardActions,
        minLines,
        maxLines,
        onChange
    )

    final override fun onChange(value: TextFieldValue) {
        _onChange(value)
    }

    override fun copy(
        value: TextFieldValue,
        enabled: Boolean,
        readOnly: Boolean,
        isError: Boolean,
        visualTransformation: VisualTransformation,
        keyboardOptions: KeyboardOptions,
        keyboardActions: KeyboardActions,
        minLines: Int,
        maxLines: Int
    ) = SimpleTextFieldState(
        value = value,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        minLines = minLines,
        maxLines = maxLines,
        _onChange = _onChange
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
                    other.minLines == minLines &&
                    other.maxLines == maxLines &&
                    other._onChange == _onChange
            )


    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + enabled.hashCode()
        result = 31 * result + readOnly.hashCode()
        result = 31 * result + isError.hashCode()
        result = 31 * result + visualTransformation.hashCode()
        result = 31 * result + keyboardOptions.hashCode()
        result = 31 * result + keyboardActions.hashCode()
        result = 31 * result + minLines
        result = 31 * result + maxLines
        result = 31 * result + _onChange.hashCode()
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
        "minLines=$minLines",
        "maxLines=$maxLines",
        "_onChange=$_onChange"
    ).joinToString(", ")
}
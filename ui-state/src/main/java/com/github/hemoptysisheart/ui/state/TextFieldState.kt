package com.github.hemoptysisheart.ui.state

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import java.util.UUID

/**
 * 텍스트 필드의 상태.
 */
interface TextFieldState {
    /**
     * 텍스트 필드의 고유 키.
     */
    val key: UUID

    /**
     * 텍스트 필드의 현재 값.
     */
    val value: TextFieldValue

    val enabled: Boolean

    val readOnly: Boolean

    val isError: Boolean

    val visualTransformation: VisualTransformation

    val keyboardOptions: KeyboardOptions

    val keyboardActions: KeyboardActions

    val lines: TextLines

    fun onFocusedChange(focusState: FocusState)

    /**
     * 텍스트 필드의 값이 변경되었을 때 호출되는 콜백.
     *
     * @param value 변경된 값.
     */
    fun onValueChange(value: TextFieldValue)

    /**
     * 텍스트 필드의 상태를 복사한 새로운 상태를 반환.
     *
     * @param value 새로운 값.
     * @return 복사한 새로운 상태.
     */
    fun copy(
        value: TextFieldValue = this.value,
        enabled: Boolean = this.enabled,
        readOnly: Boolean = this.readOnly,
        isError: Boolean = this.isError,
        visualTransformation: VisualTransformation = this.visualTransformation,
        keyboardOptions: KeyboardOptions = this.keyboardOptions,
        keyboardActions: KeyboardActions = this.keyboardActions,
        lines: TextLines = this.lines
    ): SimpleTextFieldState
}

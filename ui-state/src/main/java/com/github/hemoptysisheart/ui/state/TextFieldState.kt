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

    /**
     * 포커스 상태가 변경되었을 때 호출하는 이벤트 핸들러..
     *
     * @param newState 새 포커스 상태.
     * @param callback 포커스 상태가 변경된 후 실행할 콜백. 이벤트 처리 중에서 `@Composable`에서 맡은 기능을 여기에 넣으면 된다.
     */
    fun onFocusChange(newState: FocusState, callback: (() -> Unit)? = null)

    /**
     * 텍스트 필드의 값이 변경되었을 때 호출하는 이벤트 핸들러.
     *
     * @param newValue 변경된 값.
     * @param callback 값이 변경된 후 실행할 콜백. 이벤트 처리 중에서 `@Composable`에서 맡은 기능을 여기에 넣으면 된다.
     */
    fun onValueChange(newValue: TextFieldValue, callback: (() -> Unit)? = null)

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

    /**
     * 텍스트 필드의 상태를 복사한 새로운 상태를 반환.
     *
     * @param text 새로운 값.
     * @return 복사한 새로운 상태.
     */
    fun copy(
        text: String = this.value.text,
        enabled: Boolean = this.enabled,
        readOnly: Boolean = this.readOnly,
        isError: Boolean = this.isError,
        visualTransformation: VisualTransformation = this.visualTransformation,
        keyboardOptions: KeyboardOptions = this.keyboardOptions,
        keyboardActions: KeyboardActions = this.keyboardActions,
        lines: TextLines = this.lines
    ): SimpleTextFieldState
}

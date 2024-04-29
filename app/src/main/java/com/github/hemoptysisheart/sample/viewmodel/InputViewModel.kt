package com.github.hemoptysisheart.sample.viewmodel

import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.hemoptysisheart.ui.state.SimpleTextFieldState
import com.github.hemoptysisheart.ui.state.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InputViewModel : ViewModel() {
    companion object {
        private const val TAG = "InputViewModel"

        const val DEFAULT_WIDTH = 10
        const val DEFAULT_HEIGHT = 10
    }

    private val _width = MutableStateFlow(
        SimpleTextFieldState(
            text = "$DEFAULT_WIDTH",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = ::onChangeWidth
        )
    )
    val width: StateFlow<TextFieldState> = _width

    private val _height = MutableStateFlow(
        SimpleTextFieldState(
            text = "$DEFAULT_HEIGHT",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            onValueChange = ::onChangeHeight
        )
    )
    val height: StateFlow<TextFieldState> = _height

    fun onChangeWidth(value: TextFieldValue) {
        Log.w(TAG, "#onChangeWidth called : TODO")

        viewModelScope.launch {
            _width.emit(_width.value.copy(value = value))
        }
    }

    fun onChangeHeight(value: TextFieldValue) {
        Log.w(TAG, "#onChangeHeight called : TODO")

        viewModelScope.launch {
            _height.emit(_height.value.copy(value = value))
        }
    }
}

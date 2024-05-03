package com.github.hemoptysisheart.sample.viewmodel

import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.github.hemoptysisheart.sample.model.SampleModel
import com.github.hemoptysisheart.ui.state.InteractionImpact.BLOCKING
import com.github.hemoptysisheart.ui.state.InteractionImpact.VISIBLE
import com.github.hemoptysisheart.ui.state.SimpleTextFieldState
import com.github.hemoptysisheart.ui.state.TextFieldState
import com.github.hemoptysisheart.viewmodel.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SelectSizeViewModel @Inject constructor(
    private val sampleModel: SampleModel
) : ViewModel("SelectSizeViewModel") {
    companion object {
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

    init {
        Log.d(tag, "#init : sampleModel=$sampleModel")
    }

    /**
     * TODO 단위테스트 작성 후 `@Suppress("MemberVisibilityCanBePrivate")` 제거.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun onChangeWidth(value: TextFieldValue) {
        Log.w(tag, "#onChangeWidth args : value=$value")

        launch {
            _width.update { it.copy(value = value) }
        }
    }

    /**
     * TODO 단위테스트 작성 후 `@Suppress("MemberVisibilityCanBePrivate")` 제거.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun onChangeHeight(value: TextFieldValue) {
        Log.w(tag, "#onChangeHeight args : value=$value")

        launch {
            _height.update { it.copy(value = value) }
        }
    }

    fun onClickGenerate(onComplete: () -> Unit) {
        Log.d(tag, "#onClickGenerate args : onComplete=$onComplete")

        launch(BLOCKING) {
            delay(2_000)
            onComplete()
        }
    }

    fun onClickDefault() {
        Log.d(tag, "#onClickDefault called.")

        async(VISIBLE) {
            delay(2_000)
            _width.update { it.copy(TextFieldValue("$DEFAULT_WIDTH")) }
            _height.update { it.copy(TextFieldValue("$DEFAULT_HEIGHT")) }
        }.invokeOnCompletion { e ->
            Log.w(tag, "#onClickDefault.invokeOnCompletion args : e=$e", e)
        }
    }

    override fun toString() = listOf(
        "sampleModel=$sampleModel",
        "width=$width",
        "height=$height"
    ).joinToString(", ", "$tag(${super.toString()}", ")")
}

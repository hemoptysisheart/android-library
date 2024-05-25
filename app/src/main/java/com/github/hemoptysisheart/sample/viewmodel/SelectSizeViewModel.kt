package com.github.hemoptysisheart.sample.viewmodel

import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.github.hemoptysisheart.sample.R
import com.github.hemoptysisheart.sample.domain.Maze.Companion.HEIGHT_DEFAULT
import com.github.hemoptysisheart.sample.domain.Maze.Companion.WIDTH_DEFAULT
import com.github.hemoptysisheart.sample.model.FallbackViewModelScopeExceptionHandler
import com.github.hemoptysisheart.sample.model.MazeHolder
import com.github.hemoptysisheart.sample.ui.navigation.HistoryNavigator
import com.github.hemoptysisheart.sample.ui.navigation.SelectSizeNavigator
import com.github.hemoptysisheart.ui.state.IconState
import com.github.hemoptysisheart.ui.state.InteractionImpact.BLOCKING
import com.github.hemoptysisheart.ui.state.InteractionImpact.VISIBLE
import com.github.hemoptysisheart.ui.state.ParsableTextFieldState
import com.github.hemoptysisheart.ui.state.TextState
import com.github.hemoptysisheart.ui.state.scaffold.NavigationBarItemState
import com.github.hemoptysisheart.ui.state.scaffold.NavigationBarState
import com.github.hemoptysisheart.ui.state.scaffold.TitleTopBarState
import com.github.hemoptysisheart.viewmodel.ScaffoldContentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SelectSizeViewModel @Inject constructor(
    fallbackViewModelScopeExceptionHandler: FallbackViewModelScopeExceptionHandler,
    private val mazeHolder: MazeHolder
) : ScaffoldContentViewModel<TitleTopBarState, NavigationBarState>(
    tag = "SelectSizeViewModel",
    fallbackCoroutineExceptionHandler = fallbackViewModelScopeExceptionHandler,
    topBar = TitleTopBarState(TextState(text = "Select Size", textAlign = TextAlign.Center)),
    bottomBar = NavigationBarState(
        listOf(
            NavigationBarItemState(
                destination = SelectSizeNavigator.id,
                selected = true,
                icon = IconState(resourceId = R.drawable.ic_launcher_foreground),
                label = TextState(text = "Maze")
            ),
            NavigationBarItemState(
                destination = HistoryNavigator.id,
                selected = false,
                icon = IconState(resourceId = R.drawable.ic_launcher_background),
                label = TextState(text = "History")
            )
        )
    )
) {
    private val _width = MutableStateFlow(
        ParsableTextFieldState(
            value = WIDTH_DEFAULT,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = ::onChangeWidth,
            parser = { it.toInt(10) }
        )
    )
    val width: StateFlow<ParsableTextFieldState<Int>> = _width

    private val _height = MutableStateFlow(
        ParsableTextFieldState(
            value = HEIGHT_DEFAULT,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            onValueChange = ::onChangeHeight,
            parser = { it.toInt(10) }
        )
    )
    val height: StateFlow<ParsableTextFieldState<Int>> = _height

    /**
     * TODO 단위테스트 작성 후 `@Suppress("MemberVisibilityCanBePrivate")` 제거.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun onChangeWidth(value: TextFieldValue, callback: (() -> Unit)? = null) {
        Log.w(tag, "#onChangeWidth args : value=$value")

        launch {
            // TODO 최소값 확인.
            _width.update { it.copy(value = value) }
        }
    }

    /**
     * TODO 단위테스트 작성 후 `@Suppress("MemberVisibilityCanBePrivate")` 제거.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun onChangeHeight(value: TextFieldValue, callback: (() -> Unit)? = null) {
        Log.w(tag, "#onChangeHeight args : value=$value")

        launch {
            // TODO 최소값 확인.
            _height.update { it.copy(value = value) }
        }
    }

    fun onClickGenerate(callback: () -> Unit) {
        Log.d(tag, "#onClickGenerate args : callback=$callback")

        launch {
            val wait = async(BLOCKING) { delay(2000) }
            mazeHolder.generate(_width.value.parse(), _height.value.parse())
            wait.await()
            callback()
        }
    }

    fun onClickDefault() {
        Log.d(tag, "#onClickDefault called.")

        async(VISIBLE) {
            delay(2_000)
            _width.update { it.copy("$WIDTH_DEFAULT") }
            _height.update { it.copy("$HEIGHT_DEFAULT") }
        }.invokeOnCompletion { e ->
            Log.w(tag, "#onClickDefault.invokeOnCompletion args : e=$e", e)
        }
    }

    override fun toString() = listOf(
        super.toString(),
        "width=${width.value}",
        "height=${height.value}"
    ).joinToString(", ", "$tag(", ")")
}

package com.github.hemoptysisheart.sample.viewmodel

import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.SavedStateHandle
import com.github.hemoptysisheart.sample.model.FallbackViewModelScopeExceptionHandler
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator.Companion.ARG_HEIGHT
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator.Companion.ARG_WIDTH
import com.github.hemoptysisheart.ui.state.TextState
import com.github.hemoptysisheart.ui.state.scaffold.BottomBarState
import com.github.hemoptysisheart.ui.state.scaffold.TitleTopBarState
import com.github.hemoptysisheart.viewmodel.ScaffoldContentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MazeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    fallbackViewModelScopeExceptionHandler: FallbackViewModelScopeExceptionHandler
) : ScaffoldContentViewModel<TitleTopBarState, BottomBarState>(
    tag = "MazeViewModel",
    fallbackCoroutineExceptionHandler = fallbackViewModelScopeExceptionHandler,
    topBar = TitleTopBarState(TextState(text = "Maze", textAlign = TextAlign.Center)),
) {
    val width: Int = checkNotNull(savedStateHandle[ARG_WIDTH]).toString().toInt(10)
    val height: Int = checkNotNull(savedStateHandle[ARG_HEIGHT]).toString().toInt(10)

    override fun toString() = listOf(
        super.toString(),
        "width=$width",
        "height=$height"
    ).joinToString(", ", "$tag(", ")")
}

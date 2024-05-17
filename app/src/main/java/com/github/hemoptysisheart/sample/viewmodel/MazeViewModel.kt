package com.github.hemoptysisheart.sample.viewmodel

import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.SavedStateHandle
import com.github.hemoptysisheart.sample.model.FallbackViewModelScopeExceptionHandler
import com.github.hemoptysisheart.sample.model.MazeHolder
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
    fallbackViewModelScopeExceptionHandler: FallbackViewModelScopeExceptionHandler,
    private val mazeHolder: MazeHolder
) : ScaffoldContentViewModel<TitleTopBarState, BottomBarState>(
    tag = "MazeViewModel",
    fallbackCoroutineExceptionHandler = fallbackViewModelScopeExceptionHandler,
    topBar = TitleTopBarState(TextState(text = "Maze", textAlign = TextAlign.Center)),
) {
    val width: Int = checkNotNull(savedStateHandle[ARG_WIDTH]).toString().toInt(10)
    val height: Int = checkNotNull(savedStateHandle[ARG_HEIGHT]).toString().toInt(10)
    val maze = mazeHolder.maze

    init {
        when {
            maze == null ->
                throw IllegalStateException("maze is null.")

            maze.width != width || maze.height != height ->
                throw IllegalStateException("maze is not match. maze=$maze, width=$width, height=$height")
        }
    }

    override fun toString() = listOf(
        super.toString(),
        "width=$width",
        "height=$height",
        "maze=$maze"
    ).joinToString(", ", "$tag(", ")")
}

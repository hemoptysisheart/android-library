package com.github.hemoptysisheart.sample.viewmodel

import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.SavedStateHandle
import com.github.hemoptysisheart.sample.model.FallbackViewModelScopeExceptionHandler
import com.github.hemoptysisheart.sample.model.MazeHolder
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator.Companion.ARG_HEIGHT
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator.Companion.ARG_WIDTH
import com.github.hemoptysisheart.sample.ui.state.MazeState
import com.github.hemoptysisheart.ui.state.TextState
import com.github.hemoptysisheart.ui.state.scaffold.BottomBarState
import com.github.hemoptysisheart.ui.state.scaffold.TitleTopBarState
import com.github.hemoptysisheart.viewmodel.ScaffoldContentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _maze: MutableStateFlow<MazeState>
    val maze: StateFlow<MazeState>
        get() = _maze

    init {
        val maze = mazeHolder.maze
        when {
            maze == null ->
                throw IllegalStateException("maze is null.")

            maze.width != width || maze.height != height ->
                throw IllegalStateException("maze is not match. maze=$maze, width=$width, height=$height")

            else ->
                _maze = MutableStateFlow(MazeState(width, height, maze))
        }
    }

    override fun toString() = listOf(
        super.toString(),
        "width=$width",
        "height=$height",
        "maze=${maze.value}"
    ).joinToString(", ", "$tag(", ")")
}

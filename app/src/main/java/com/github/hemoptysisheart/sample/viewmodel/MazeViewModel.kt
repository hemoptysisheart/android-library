package com.github.hemoptysisheart.sample.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.github.hemoptysisheart.sample.model.FallbackViewModelScopeExceptionHandler
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator.Companion.ARG_HEIGHT
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator.Companion.ARG_WIDTH
import com.github.hemoptysisheart.ui.state.scaffold.SimpleTopBarState
import com.github.hemoptysisheart.viewmodel.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MazeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    fallbackViewModelScopeExceptionHandler: FallbackViewModelScopeExceptionHandler
) : ViewModel(
    tag = "MazeViewModel",
    fallbackCoroutineExceptionHandler = fallbackViewModelScopeExceptionHandler,
    topBar = SimpleTopBarState(
        enableBack = true,
        title = "Maze",
        _back = { navigator -> }
    )
) {
    val width: Int = checkNotNull(savedStateHandle[ARG_WIDTH]).toString().toInt(10)
    val height: Int = checkNotNull(savedStateHandle[ARG_HEIGHT]).toString().toInt(10)

    override fun toString() = listOf<String>(
    ).joinToString(", ", "$tag(${super.toString()}", ")")
}

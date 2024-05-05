package com.github.hemoptysisheart.sample.viewmodel

import com.github.hemoptysisheart.sample.model.FallbackViewModelScopeExceptionHandler
import com.github.hemoptysisheart.ui.state.SimpleTopBarState
import com.github.hemoptysisheart.viewmodel.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MazeViewModel @Inject constructor(
    fallbackViewModelScopeExceptionHandler: FallbackViewModelScopeExceptionHandler
) : ViewModel(
    tag = "MazeViewModel",
    fallbackCoroutineExceptionHandler = fallbackViewModelScopeExceptionHandler,
    topBar = SimpleTopBarState(
        enableBackward = true,
        title = "Maze"
    )
) {
    override fun toString() = listOf<String>(
    ).joinToString(", ", "$tag(${super.toString()}", ")")
}

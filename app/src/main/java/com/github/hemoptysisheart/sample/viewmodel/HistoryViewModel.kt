package com.github.hemoptysisheart.sample.viewmodel

import com.github.hemoptysisheart.ui.state.SimpleTopBarState
import com.github.hemoptysisheart.viewmodel.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel(
    tag = "HistoryViewModel",
    topBar = SimpleTopBarState(enableBackward = false, title = "History")
) {
    /**
     * [androidx.lifecycle.ViewModel]의 [CoroutineExceptionHandler] 테스트용 메서드.
     */
    fun onClickError() {
        launch {
            delay(1_000L)
            throw RuntimeException("Test exception")
        }
    }

    override fun toString() = listOf<String>(
    ).joinToString(", ", "$tag(${super.toString()}", ")")
}

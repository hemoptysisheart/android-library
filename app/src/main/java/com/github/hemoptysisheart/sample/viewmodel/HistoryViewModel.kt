package com.github.hemoptysisheart.sample.viewmodel

import android.util.Log
import com.github.hemoptysisheart.sample.model.FallbackViewModelScopeExceptionHandler
import com.github.hemoptysisheart.ui.state.InteractionImpact.VISIBLE
import com.github.hemoptysisheart.ui.state.SimpleTopBarState
import com.github.hemoptysisheart.viewmodel.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    fallbackViewModelScopeExceptionHandler: FallbackViewModelScopeExceptionHandler
) : ViewModel(
    tag = "HistoryViewModel",
    fallbackCoroutineExceptionHandler = fallbackViewModelScopeExceptionHandler,
    topBar = SimpleTopBarState(enableBackward = false, title = "History")
) {
    /**
     * [androidx.lifecycle.ViewModel]의 [CoroutineExceptionHandler] 테스트용 메서드.
     */
    fun onClickError() {
        Log.d(tag, "#onClickError called.")
        launch(impact = VISIBLE) {
            delay(1_000L)
            throw RuntimeException("Test exception")
        }
    }

    override fun toString() = listOf<String>(
    ).joinToString(", ", "$tag(${super.toString()}", ")")
}

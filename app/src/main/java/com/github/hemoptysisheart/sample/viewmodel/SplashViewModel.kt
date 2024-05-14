package com.github.hemoptysisheart.sample.viewmodel

import androidx.lifecycle.LifecycleOwner
import com.github.hemoptysisheart.sample.model.FallbackCoroutineExceptionHandler
import com.github.hemoptysisheart.ui.state.scaffold.BottomBarState
import com.github.hemoptysisheart.ui.state.scaffold.TopBarState
import com.github.hemoptysisheart.viewmodel.ScaffoldContentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    fallbackCoroutineExceptionHandler: FallbackCoroutineExceptionHandler
) : ScaffoldContentViewModel<TopBarState, BottomBarState>("SplashViewModel", fallbackCoroutineExceptionHandler) {
    companion object {
        const val MIN_SPLASH_TIME = 3_000L
    }

    private val _timeout = MutableStateFlow(false)

    /**
     * Splash 화면 표시 시간이 끝났는지 여부.
     */
    val timeout: StateFlow<Boolean> = _timeout

    override fun doOnStart(owner: LifecycleOwner) {
        launch {
            delay(MIN_SPLASH_TIME)
            _timeout.emit(true)
        }
    }

    override fun toString() = listOf(
        "timeout=${timeout.value}"
    ).joinToString(", ", "$tag(", ")")
}

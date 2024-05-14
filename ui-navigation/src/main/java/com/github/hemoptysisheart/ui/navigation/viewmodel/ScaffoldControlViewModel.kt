package com.github.hemoptysisheart.ui.navigation.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import com.github.hemoptysisheart.statepump.ScaffoldPump
import com.github.hemoptysisheart.ui.state.DialogState
import com.github.hemoptysisheart.ui.state.scaffold.BottomBarState
import com.github.hemoptysisheart.ui.state.scaffold.TopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * [com.github.hemoptysisheart.ui.navigation.compose.Scaffold]를 제어할 수 있는 뷰모델.
 */
@HiltViewModel
class ScaffoldControlViewModel @Inject constructor(
    private val scaffoldPump: ScaffoldPump
) : ViewModel(), DefaultLifecycleObserver {
    companion object {
        private const val TAG = "ScaffoldControlViewModel"
    }

    /**
     * 당이얼로그 내용. `null`이면 비표시.
     */
    val dialog: StateFlow<DialogState?> = scaffoldPump.dialog

    /**
     * 상단바 상태. `null`이면 비표시.
     */
    val topBar: StateFlow<TopBarState?> = scaffoldPump.topBar

    /**
     * 진행 표시기 표시 여부.
     *
     * @see androidx.compose.material3.LinearProgressIndicator
     */
    val visibleProgress: StateFlow<Boolean> = scaffoldPump.visibleProgress

    /**
     * 하단바 상태. `null`이면 비표시.
     */
    val bottomBar: StateFlow<BottomBarState?> = scaffoldPump.bottomBar

    override fun toString() = listOf(
        "dialog=${dialog.value}",
        "topBar=${topBar.value}",
        "visibleProgress=${visibleProgress.value}",
        "bottomBar=${bottomBar.value}"
    ).joinToString(", ", "$TAG(", ")")
}

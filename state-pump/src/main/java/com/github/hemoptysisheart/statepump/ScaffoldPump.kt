package com.github.hemoptysisheart.statepump

import com.github.hemoptysisheart.ui.state.scaffold.BottomBarState
import com.github.hemoptysisheart.ui.state.scaffold.TopBarState
import kotlinx.coroutines.flow.StateFlow

/**
 * [androidx.compose.material3.Scaffold]의 `content`를 제외한 상태 정보를 전달한다.
 */
interface ScaffoldPump {
    /**
     * 현재 화면이 표시해야 할 상단바 상태.
     *
     * @see update
     */
    val topBar: StateFlow<TopBarState?>

    /**
     * 현재 화면이 표시해야 할 하단바 상태.
     *
     * @see update
     */
    val bottomBar: StateFlow<BottomBarState?>

    /**
     * 상단바 상태를 갱신한다.
     *
     * @param topBar 새 상단바 상태.
     *
     * @see ScaffoldPump.topBar
     */
    fun update(topBar: TopBarState?)

    /**
     * 하단바 상태를 갱신한다.
     *
     * @param bottomBar 새 하단바 상태.
     *
     * @see ScaffoldPump.bottomBar
     */
    fun update(bottomBar: BottomBarState?)
}

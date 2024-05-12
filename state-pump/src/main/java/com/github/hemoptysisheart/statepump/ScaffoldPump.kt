package com.github.hemoptysisheart.statepump

import com.github.hemoptysisheart.ui.state.scaffold.BottomBarState
import com.github.hemoptysisheart.ui.state.scaffold.TopBarState
import kotlinx.coroutines.flow.StateFlow

/**
 * [androidx.compose.material3.Scaffold]의 `content`를 제외한 상태 정보를 전달한다.
 *
 * 스캐폴드 컨텐트를 담당하는 뷰모델이 `topBar`, `bottomBar` 상태를 스캐폴드 레이아웃을 괄리하는 `@Composable` 콜 스택에서 상위 함수로 전달한다.
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
     * 사용자 인터랙션을 막지는 않지만 처리중임을 표시해야 하는지 여부.
     */
    val visibleProgress: StateFlow<Boolean>

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

    /**
     * 프로그레스 인디케이터(`visibleProgress`) 상태를 바꾼다.
     *
     * @param visible `true`면 프로그레스 인디케이터를 표시한다.
     */
    fun visibleProgress(visible: Boolean)
}

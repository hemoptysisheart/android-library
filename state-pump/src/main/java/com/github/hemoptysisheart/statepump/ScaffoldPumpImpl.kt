package com.github.hemoptysisheart.statepump

import android.util.Log
import com.github.hemoptysisheart.ui.state.scaffold.BottomBarState
import com.github.hemoptysisheart.ui.state.scaffold.TopBarState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * TODO `update` 함수를 suspend 함수로 변경하는 게 나을까? UI를 변경하는 함수이니 스코프를 Main으로 으로 해뒀는데 이걸로 충분한가?
 */
class ScaffoldPumpImpl : ScaffoldPump {
    companion object {
        private const val TAG = "ScaffoldPumpImpl"
    }

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _topBar = MutableStateFlow<TopBarState?>(null)

    override val topBar: StateFlow<TopBarState?> = _topBar

    private val _bottomBar = MutableStateFlow<BottomBarState?>(null)
    override val bottomBar: StateFlow<BottomBarState?> = _bottomBar

    override fun update(topBar: TopBarState?) {
        Log.d(TAG, "#update args : topBar=$topBar")

        scope.launch {
            _topBar.emit(topBar)
        }
    }

    override fun update(bottomBar: BottomBarState?) {
        Log.d(TAG, "#update args : bottomBar=$bottomBar")

        scope.launch {
            _bottomBar.emit(bottomBar)
        }
    }

    override fun toString() = listOf(
        "topBar=${topBar.value}",
        "bottomBar=${bottomBar.value}"
    ).joinToString(", ", "$TAG(", ")")
}

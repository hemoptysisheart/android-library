package com.github.hemoptysisheart.ui.state.scaffold

import com.github.hemoptysisheart.ui.state.State
import java.util.UUID

/**
 * [androidx.compose.material3.Scaffold]의`topBar`용 상태 홀더.
 *
 * @see androidx.compose.material3.Scaffold
 */
interface TopBarState : State {
    companion object {
        val EMPTY = object : TopBarState {
            override val key: UUID
                get() = throw UnsupportedOperationException("empty")
            override val testTag: String
                get() = throw UnsupportedOperationException("empty")
        }
    }
}

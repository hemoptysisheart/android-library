package com.github.hemoptysisheart.ui.state

import java.util.UUID

/**
 * `@Compose` 상태 홀더.
 */
interface State {
    /**
     * 상태 키.
     */
    val key: UUID

    /**
     * @see androidx.compose.ui.platform.testTag
     */
    val testTag: String
}

package com.github.hemoptysisheart.ui.state.scaffold

import androidx.compose.runtime.Stable
import com.github.hemoptysisheart.ui.state.TextState
import java.util.UUID

@Stable
data class TitleTopBarState(
    val title: TextState,
    override val key: UUID = UUID.randomUUID(),
    override val testTag: String = key.toString()
) : TopBarState {
    constructor(
        title: String,
        key: UUID = UUID.randomUUID(),
        testTag: String = key.toString()
    ) : this(TextState(title), key, testTag)
}

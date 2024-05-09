package com.github.hemoptysisheart.ui.state.scaffold

import androidx.compose.runtime.Stable
import java.util.UUID

@Stable
data class TitleTopBarState(
    val title: String,
    override val key: UUID = UUID.randomUUID(),
    override val testTag: String = key.toString()
) : TopBarState

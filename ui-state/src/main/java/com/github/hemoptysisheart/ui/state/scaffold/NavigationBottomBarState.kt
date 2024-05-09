package com.github.hemoptysisheart.ui.state.scaffold

import androidx.compose.runtime.Stable
import java.util.UUID


@Stable
data class NavigationBottomBarState(
    val items: List<NavigationBarItemState>,
    override val key: UUID = UUID.randomUUID(),
    override val testTag: String = key.toString()
) : BottomBarState

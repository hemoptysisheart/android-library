package com.github.hemoptysisheart.sample.ui.state

import androidx.compose.runtime.Immutable
import com.github.hemoptysisheart.sample.domain.Direction

@Immutable
data class CellState(
    val x: Int,
    val y: Int,
    val openWalls: List<Direction> = emptyList(),
    val start: Boolean = false,
    val end: Boolean = false,
    val progress: Boolean = false
)

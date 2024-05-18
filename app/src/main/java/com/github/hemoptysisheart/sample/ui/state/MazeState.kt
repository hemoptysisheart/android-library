package com.github.hemoptysisheart.sample.ui.state

import androidx.compose.runtime.Immutable

@Immutable
data class MazeState(
    val width: Int,
    val height: Int,
    val cells: List<CellState>
)

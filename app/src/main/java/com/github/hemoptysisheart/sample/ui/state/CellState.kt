package com.github.hemoptysisheart.sample.ui.state

import androidx.compose.runtime.Immutable
import com.github.hemoptysisheart.sample.domain.Cell

@Immutable
data class CellState(
    private val cell: Cell
) {
    val x = cell.x
    val y = cell.y
}

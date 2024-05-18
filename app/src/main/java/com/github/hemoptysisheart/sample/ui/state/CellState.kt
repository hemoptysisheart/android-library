package com.github.hemoptysisheart.sample.ui.state

import androidx.compose.runtime.Immutable
import com.github.hemoptysisheart.sample.domain.Cell
import com.github.hemoptysisheart.sample.domain.Direction

@Immutable
data class CellState(
    private val cell: Cell,
    val openWalls: List<Direction>
) {
    val x = cell.x
    val y = cell.y

}

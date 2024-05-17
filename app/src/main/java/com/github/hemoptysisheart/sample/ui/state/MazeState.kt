package com.github.hemoptysisheart.sample.ui.state

import androidx.compose.runtime.Immutable
import com.github.hemoptysisheart.sample.domain.Maze

@Immutable
data class MazeState(
    val width: Int,
    val height: Int,
    private val maze: Maze
) {
    val cells: List<CellState> = maze.cells.map { CellState(it) }
}

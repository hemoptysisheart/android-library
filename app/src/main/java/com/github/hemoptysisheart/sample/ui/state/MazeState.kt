package com.github.hemoptysisheart.sample.ui.state

import androidx.compose.runtime.Immutable
import com.github.hemoptysisheart.sample.domain.Maze

@Immutable
data class MazeState(
    private val maze: Maze
) {
    val width: Int = maze.width
    val height: Int = maze.height
    val cells: List<CellState> = maze.cells
        .map { cell ->
            CellState(
                cell = cell,
                openWalls = maze.links(cell)
                    .mapNotNull { cell.direction(it) }
            )
        }
}

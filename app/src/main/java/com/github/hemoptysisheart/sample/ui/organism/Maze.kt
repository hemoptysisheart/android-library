package com.github.hemoptysisheart.sample.ui.organism

import android.util.Log
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.hemoptysisheart.sample.domain.Maze
import com.github.hemoptysisheart.sample.domain.Maze.Companion.HEIGHT_DEFAULT
import com.github.hemoptysisheart.sample.domain.Maze.Companion.WIDTH_DEFAULT
import com.github.hemoptysisheart.sample.ui.state.CellState
import com.github.hemoptysisheart.sample.ui.state.MazeState
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.ui.compose.preview.PreviewComponentNoText

@Composable
fun Maze(maze: MazeState, modifier: Modifier = Modifier, onClickCell: (CellState) -> Unit = {}) {
    Log.v(TAG, "#Maze args : maze=$maze")

    LazyVerticalGrid(
        columns = GridCells.Fixed(maze.width),
        modifier = modifier
    ) {
        items(items = maze.cells) {
            Cell(it, onClickCell)
        }
    }
}

@Composable
@PreviewComponentNoText
fun MazePreview() {
    val maze = Maze(WIDTH_DEFAULT, HEIGHT_DEFAULT)
    AndroidLibraryTheme {
        Maze(MazeState(
            width = maze.width,
            height = maze.height,
            cells = maze.cells.map {
                CellState(
                    x = it.x,
                    y = it.y,
                    openWalls = maze.links(it).mapNotNull { cell -> it.direction(cell) },
                    start = it == maze.start,
                    end = it == maze.end
                )
            }
        ))
    }
}

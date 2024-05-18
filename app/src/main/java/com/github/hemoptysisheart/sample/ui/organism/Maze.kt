package com.github.hemoptysisheart.sample.ui.organism

import android.util.Log
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.hemoptysisheart.sample.domain.Maze
import com.github.hemoptysisheart.sample.domain.Maze.Companion.HEIGHT_DEFAULT
import com.github.hemoptysisheart.sample.domain.Maze.Companion.WIDTH_DEFAULT
import com.github.hemoptysisheart.sample.ui.state.MazeState
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme

@Composable
fun Maze(maze: MazeState) {
    Log.v(TAG, "#Maze args : maze=$maze")

    LazyVerticalGrid(
        columns = GridCells.Fixed(maze.width),
        modifier = Modifier
            .sizeIn(minWidth = (CELL_SIZE * maze.width).dp, minHeight = (CELL_SIZE * maze.height).dp)
    ) {
        items(items = maze.cells) {
            Cell(it)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MazePreview() {
    AndroidLibraryTheme {
        Maze(MazeState(Maze(WIDTH_DEFAULT, HEIGHT_DEFAULT)))
    }
}

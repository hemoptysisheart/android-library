package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.PreviewActivity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.hemoptysisheart.sample.domain.Maze
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator
import com.github.hemoptysisheart.sample.ui.state.MazeState
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.sample.viewmodel.MazeViewModel
import com.github.hemoptysisheart.ui.navigation.compose.baseNavigator
import com.github.hemoptysisheart.ui.navigation.compose.baseViewModel

@Composable
fun MazePage(
    navigator: MazeNavigator,
    viewModel: MazeViewModel = baseViewModel()
) {
    Log.v(TAG, "#MazePage args : navigator=$navigator, viewModel=$viewModel")

    val maze by viewModel.maze.collectAsStateWithLifecycle()

    MazePageContent(
        navigator = navigator,
        maze = maze
    )
}

@Composable
private fun MazePageContent(
    navigator: MazeNavigator,
    maze: MazeState
) {
    Log.v(TAG, "#MazePageContent args : navigator=$navigator, maze=$maze")

    LazyVerticalGrid(
        columns = GridCells.Fixed(maze.width),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(items = maze.cells) { cell ->
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "${cell.x}, ${cell.y}",
                    modifier = Modifier.sizeIn(minWidth = 60.dp, minHeight = 60.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun MazeScreenPreview() {
    val width = Maze.WIDTH_DEFAULT
    val height = Maze.HEIGHT_DEFAULT
    AndroidLibraryTheme {
        MazePageContent(
            navigator = MazeNavigator(baseNavigator(PreviewActivity())),
            maze = MazeState(
                width = width,
                height = height,
                maze = Maze(width, height)
            )
        )
    }
}

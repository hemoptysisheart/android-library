package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.PreviewActivity
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.hemoptysisheart.sample.domain.Maze
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator
import com.github.hemoptysisheart.sample.ui.organism.Maze
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

    Maze(maze = maze)
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
                maze = Maze(width, height)
            )
        )
    }
}

package com.github.hemoptysisheart.sample.ui.page

import android.content.res.Configuration
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.PreviewActivity
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.hemoptysisheart.sample.domain.Maze
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator
import com.github.hemoptysisheart.sample.ui.organism.Maze
import com.github.hemoptysisheart.sample.ui.state.CellState
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
        maze = maze,
        onClickCell = viewModel::onClickCell
    )
}

@Composable
private fun MazePageContent(
    navigator: MazeNavigator,
    maze: MazeState,
    onClickCell: (CellState) -> Unit = {},
) {
    Log.v(TAG, "#MazePageContent args : navigator=$navigator, maze=$maze")

    Maze(maze = maze, onClickCell = onClickCell)
}

@Composable
@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MazeScreenPreview() {
    val maze = Maze(Maze.WIDTH_DEFAULT, Maze.HEIGHT_DEFAULT)
    AndroidLibraryTheme {
        MazePageContent(
            navigator = MazeNavigator(baseNavigator(PreviewActivity())),
            maze = MazeState(
                width = maze.width,
                height = maze.height,
                cells = maze.cells.map {
                    CellState(
                        x = it.x,
                        y = it.y,
                        openWalls = maze.links(it).mapNotNull { cell -> it.direction(cell) }
                    )
                }
            )
        )
    }
}

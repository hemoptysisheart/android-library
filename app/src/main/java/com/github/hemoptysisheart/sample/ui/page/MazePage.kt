package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.PreviewActivity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator
import com.github.hemoptysisheart.sample.ui.template.scaffold.TopBar
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.sample.viewmodel.MazeViewModel
import com.github.hemoptysisheart.ui.navigation.compose.baseNavigator
import com.github.hemoptysisheart.ui.navigation.compose.viewModel
import com.github.hemoptysisheart.ui.state.SimpleTopBarState
import com.github.hemoptysisheart.ui.state.TopBarState

@Composable
fun MazePage(
    navigator: MazeNavigator,
    viewModel: MazeViewModel = viewModel()
) {
    Log.v(TAG, "#MazePage args : navigator=$navigator, viewModel=$viewModel")

    val topBar by viewModel.topBar.collectAsStateWithLifecycle()

    MazePageContent(navigator, topBar, viewModel.width, viewModel.height)
}

@Composable
private fun MazePageContent(navigator: MazeNavigator, topBar: TopBarState, width: Int, height: Int) {
    Log.v(TAG, "#MazePageContent args : navigator=$navigator, topBar=$topBar, width=$width, height=$height")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navigator, topBar) }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(width),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(width * height) { index ->
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "index=$index, x=${index % width}, y=${index / width}",
                        modifier = Modifier.sizeIn(minWidth = 60.dp, minHeight = 60.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun MazeScreenPreview() {
    AndroidLibraryTheme {
        MazePageContent(
            navigator = MazeNavigator(baseNavigator(PreviewActivity())),
            topBar = SimpleTopBarState(true, "Maze"),
            width = 7,
            height = 13
        )
    }
}

package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator
import com.github.hemoptysisheart.sample.ui.template.scaffold.BottomBar
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

    MazePageContent(navigator, topBar)
}

@Composable
private fun MazePageContent(navigator: MazeNavigator, topBar: TopBarState) {
    Log.v(TAG, "#MazePageContent args : navigator=$navigator, topBar=$topBar")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(rememberNavController(), topBar) },
        bottomBar = { BottomBar(rememberNavController()) }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(10),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(10 * 13) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "☐")
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
            topBar = SimpleTopBarState(true, "Maze")
        )
    }
}

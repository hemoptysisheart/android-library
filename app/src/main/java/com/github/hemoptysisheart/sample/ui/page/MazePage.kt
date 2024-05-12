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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.PreviewActivity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.hemoptysisheart.sample.ui.navigation.MazeNavigator
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

    MazePageContent(
        navigator = navigator,
        width = viewModel.width,
        height = viewModel.height
    )
}

@Composable
private fun MazePageContent(
    navigator: MazeNavigator,
    width: Int,
    height: Int
) {
    Log.v(TAG, "#MazePageContent args : navigator=$navigator, width=$width, height=$height")

    Scaffold(
        modifier = Modifier.fillMaxSize()
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
            width = 7,
            height = 13
        )
    }
}

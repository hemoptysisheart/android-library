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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.template.BottomBar
import com.github.hemoptysisheart.sample.ui.template.TopBar
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.sample.viewmodel.MazeViewModel
import com.github.hemoptysisheart.ui.navigation.viewModel

@Composable
fun MazeScreen(
    navController: NavHostController,
    viewModel: MazeViewModel = viewModel()
) {
    Log.v(TAG, "#MazeScreen args : navController=$navController, viewModel=$viewModel")

    MazeScreenContent(navController = navController)
}

@Composable
private fun MazeScreenContent(navController: NavHostController) {
    Log.v(TAG, "#MazeScreenContent args : navController=$navController")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController) },
        bottomBar = { BottomBar(navController) }
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
        MazeScreenContent(rememberNavController())
    }
}

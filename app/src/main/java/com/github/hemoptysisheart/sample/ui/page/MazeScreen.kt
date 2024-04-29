package com.github.hemoptysisheart.sample.ui.page

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme

@Composable
fun MazeScreen(navController: NavHostController) {
    LazyVerticalGrid(columns = GridCells.FixedSize(40.dp)) {
        items(40 * 40) {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "☐")
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun MazeScreenPreview() {
    AndroidLibraryTheme {
        MazeScreen(rememberNavController())
    }
}

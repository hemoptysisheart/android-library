package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.template.BottomBar
import com.github.hemoptysisheart.sample.ui.template.TopBar
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.sample.viewmodel.HistoryViewModel
import com.github.hemoptysisheart.ui.navigation.viewModel

@Composable
fun HistoryPage(
    navController: NavHostController,
    viewModel: HistoryViewModel = viewModel()
) {
    HistoryPageContent(navController)
}

@Composable
private fun HistoryPageContent(navController: NavHostController) {
    Log.v(TAG, "#HistoryPageContent args : navController=$navController")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController) },
        bottomBar = { BottomBar(navController) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "History")
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun HistoryPageContentPreview() {
    AndroidLibraryTheme {
        HistoryPageContent(rememberNavController())
    }
}

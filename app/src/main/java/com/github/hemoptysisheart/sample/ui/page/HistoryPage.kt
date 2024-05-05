package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.template.scaffold.BottomBar
import com.github.hemoptysisheart.sample.ui.template.scaffold.TopBar
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.sample.viewmodel.HistoryViewModel
import com.github.hemoptysisheart.ui.navigation.viewModel
import com.github.hemoptysisheart.ui.state.SimpleTopBarState
import com.github.hemoptysisheart.ui.state.TopBarState

@Composable
fun HistoryPage(
    navController: NavHostController,
    viewModel: HistoryViewModel = viewModel()
) {
    Log.v(TAG, "#HistoryPage args : navController=$navController, viewModel=$viewModel")

    val topBar by viewModel.topBar.collectAsStateWithLifecycle()

    HistoryPageContent(navController, topBar, viewModel::onClickError)
}

@Composable
private fun HistoryPageContent(
    navController: NavHostController,
    topBar: TopBarState,
    onClickError: () -> Unit = {}
) {
    Log.v(TAG, "#HistoryPageContent args : navController=$navController")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController, topBar) },
        bottomBar = { BottomBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "History")
            Spacer(modifier = Modifier.height(100.dp))
            Button(onClick = onClickError) {
                Text(text = "Test Error")
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun HistoryPageContentPreview() {
    AndroidLibraryTheme {
        HistoryPageContent(rememberNavController(), SimpleTopBarState(true, "History"))
    }
}

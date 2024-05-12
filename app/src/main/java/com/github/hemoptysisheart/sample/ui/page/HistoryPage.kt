package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.PreviewActivity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.hemoptysisheart.sample.ui.navigation.HistoryNavigator
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.sample.viewmodel.HistoryViewModel
import com.github.hemoptysisheart.ui.navigation.compose.baseNavigator
import com.github.hemoptysisheart.ui.navigation.compose.baseViewModel

@Composable
fun HistoryPage(
    navigator: HistoryNavigator,
    viewModel: HistoryViewModel = baseViewModel()
) {
    Log.v(TAG, "#HistoryPage args : navigator=$navigator, viewModel=$viewModel")

    val visibleProgress by viewModel.visibleProgress.collectAsStateWithLifecycle()
    val blockingProgress by viewModel.blockingProgress.collectAsStateWithLifecycle()

    HistoryPageContent(navigator, visibleProgress, blockingProgress, viewModel::onClickError)
}

@Composable
private fun HistoryPageContent(
    navigator: HistoryNavigator,
    visibleProgress: Boolean,
    blockingProgress: Boolean,
    onClickError: () -> Unit = {}
) {
    Log.v(
        TAG,
        listOf(
            "navigator=$navigator",
            "visibleProgress=$visibleProgress",
            "blockingProgress=$blockingProgress",
            "onClickError=$onClickError"
        ).joinToString(", ", "#HistoryPageContent args : ")
    )

    if (blockingProgress) {
        Dialog(onDismissRequest = { }) {
            CircularProgressIndicator()
        }
    } else if (visibleProgress) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(Float.MAX_VALUE)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
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

@Composable
@Preview(showSystemUi = true)
private fun HistoryPageContentPreview() {
    AndroidLibraryTheme {
        HistoryPageContent(
            navigator = HistoryNavigator(baseNavigator(PreviewActivity())),
            visibleProgress = false,
            blockingProgress = false
        )
    }
}

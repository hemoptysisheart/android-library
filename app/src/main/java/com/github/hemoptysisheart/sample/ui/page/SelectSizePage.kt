package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.template.scaffold.BottomBar
import com.github.hemoptysisheart.sample.ui.template.scaffold.TopBar
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.sample.viewmodel.SelectSizeViewModel
import com.github.hemoptysisheart.ui.compose.OutlinedTextField
import com.github.hemoptysisheart.ui.navigation.viewModel
import com.github.hemoptysisheart.ui.state.ParsableTextFieldState
import com.github.hemoptysisheart.ui.state.SimpleTopBarState
import com.github.hemoptysisheart.ui.state.TextFieldState
import com.github.hemoptysisheart.ui.state.TopBarState

@Composable
fun SelectSizePage(
    navController: NavHostController,
    viewModel: SelectSizeViewModel = viewModel()
) {
    Log.v(TAG, "#SelectSizePage args : navController=$navController, viewModel=$viewModel")

    val topBar by viewModel.topBar.collectAsStateWithLifecycle()
    val visibleProgress by viewModel.visibleProgress.collectAsStateWithLifecycle()
    val blockingProgress by viewModel.blockingProgress.collectAsStateWithLifecycle()
    val width by viewModel.width.collectAsStateWithLifecycle()
    val height by viewModel.height.collectAsStateWithLifecycle()

    SelectSizePageContent(
        navController,
        topBar,
        visibleProgress,
        blockingProgress,
        width,
        height,
        viewModel::onClickGenerate,
        viewModel::onClickDefault
    )
}

@Composable
private fun SelectSizePageContent(
    navController: NavHostController,
    topBar: TopBarState,
    visibleProgress: Boolean,
    blockingProgress: Boolean,
    width: TextFieldState,
    height: TextFieldState,
    onClickGenerate: (() -> Unit) -> Unit = { },
    onClickDefault: () -> Unit = { }
) {
    Log.v(
        TAG,
        listOf(
            "navController=$navController",
            "topBar=$topBar",
            "visibleProgress=$visibleProgress",
            "blockingProgress=$blockingProgress",
            "width=$width",
            "height=$height",
            "onClickGenerate=$onClickGenerate",
            "onClickDefault=$onClickDefault"
        ).joinToString(", ", "#SelectSizePage args : ")
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController, topBar) },
        bottomBar = { BottomBar(navController) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
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
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        state = width,
                        modifier = Modifier.weight(1F),
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        placeholder = {
                            Text(text = "가로", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        }
                    )
                    Text(text = "x", modifier = Modifier.padding(10.dp, 0.dp))
                    OutlinedTextField(
                        state = height,
                        modifier = Modifier.weight(1F),
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        placeholder = {
                            Text(text = "세로", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    modifier = Modifier.widthIn(200.dp),
                    onClick = {
                        onClickGenerate {
                            navController.navigate("maze")
                        }
                    }
                ) {
                    Text(text = "미로 만들기", modifier = Modifier.padding(10.dp), fontWeight = Bold)
                }
                Spacer(modifier = Modifier.height(30.dp))
                Button(modifier = Modifier.widthIn(200.dp), onClick = onClickDefault) {
                    Text(text = "기본값", modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun SelectSizePageContentPreview() {
    AndroidLibraryTheme {
        SelectSizePageContent(
            navController = rememberNavController(),
            topBar = SimpleTopBarState(true, "Select Size"),
            visibleProgress = false,
            blockingProgress = false,
            width = ParsableTextFieldState(value = TextFieldValue("10"), onValueChange = {}, _parser = { it.toInt() }),
            height = ParsableTextFieldState(value = TextFieldValue("10"), onValueChange = {}, _parser = { it.toInt() }),
            onClickGenerate = {},
            onClickDefault = {})
    }
}

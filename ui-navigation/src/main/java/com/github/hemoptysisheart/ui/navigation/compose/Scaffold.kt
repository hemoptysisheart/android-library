package com.github.hemoptysisheart.ui.navigation.compose

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.github.hemoptysisheart.ui.compose.Dialog
import com.github.hemoptysisheart.ui.state.DialogState

/**
 * 전체적으로 스캐폴드 레이아웃을 제어하는 컴포저블.
 *
 */
@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    dialog: DialogState? = null,
    visibleProgress: Boolean = false,
    topBar: @Composable () -> Unit = { },
    bottomBar: @Composable () -> Unit = { },
    snackbarHost: @Composable () -> Unit = { },
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable () -> Unit
) {
    Log.v(
        TAG,
        listOf(
            "modifier=$modifier",
            "dialog=$dialog",
            "visibleProgress=$visibleProgress",
            "topBar=$topBar",
            "bottomBar=$bottomBar",
            "snackbarHost=$snackbarHost",
            "floatingActionButton=$floatingActionButton",
            "floatingActionButtonPosition=$floatingActionButtonPosition",
            "containerColor=$containerColor",
            "contentColor=$contentColor",
            "contentWindowInsets=$contentWindowInsets",
        ).joinToString(", ", "#Scaffold args : ")
    )

    if (null != dialog) {
        Dialog(state = dialog)
    }

    androidx.compose.material3.Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (visibleProgress) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(Float.MAX_VALUE)
                )
            }
            content()
        }
    }
}

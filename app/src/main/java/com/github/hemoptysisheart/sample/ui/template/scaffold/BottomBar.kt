package com.github.hemoptysisheart.sample.ui.template.scaffold

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.PreviewActivity
import androidx.compose.ui.tooling.preview.Preview
import com.github.hemoptysisheart.sample.R
import com.github.hemoptysisheart.sample.ui.navigation.HistoryNavigator
import com.github.hemoptysisheart.sample.ui.navigation.SelectSizeNavigator
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.ui.compose.Icon
import com.github.hemoptysisheart.ui.navigation.compose.baseNavigator
import com.github.hemoptysisheart.ui.navigation.destination.Navigator
import com.github.hemoptysisheart.ui.state.IconState

@Composable
fun BottomBar(navigator: Navigator) {
    Log.v(TAG, "#BottomBar args : navigator=$navigator")

    BottomAppBar(modifier = Modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = navigator is SelectSizeNavigator,
            onClick = {
                if (navigator is HistoryNavigator) {
                    navigator.selectSize()
                }
            },
            icon = { Icon(IconState(imageVector = Default.PlayArrow)) },
            label = { Text(text = "Maze") }
        )

        NavigationBarItem(
            selected = navigator is HistoryNavigator,
            onClick = {
                if (navigator is SelectSizeNavigator) {
                    navigator.history()
                }
            },
            icon = { Icon(IconState(resourceId = R.drawable.ic_launcher_background)) },
            label = { Text(text = "History") }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun BottomBarPreview() {
    AndroidLibraryTheme {
        BottomBar(SelectSizeNavigator(baseNavigator(PreviewActivity())))
    }
}

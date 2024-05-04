package com.github.hemoptysisheart.sample.ui.template

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme

@Composable
fun BottomBar() {
    BottomAppBar(modifier = Modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(imageVector = Default.PlayArrow, contentDescription = null) },
            label = { Text(text = "Maze") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(imageVector = Default.History, contentDescription = null) },
            label = { Text(text = "History") }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun BottomBarPreview() {
    AndroidLibraryTheme {
        BottomBar()
    }
}

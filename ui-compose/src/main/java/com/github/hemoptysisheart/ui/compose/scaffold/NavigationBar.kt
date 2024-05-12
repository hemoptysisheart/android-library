package com.github.hemoptysisheart.ui.compose.scaffold

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import com.github.hemoptysisheart.ui.compose.Icon
import com.github.hemoptysisheart.ui.compose.Text
import com.github.hemoptysisheart.ui.state.scaffold.NavigationBarItemState
import com.github.hemoptysisheart.ui.state.scaffold.NavigationBarState

@Composable
fun NavigationBar(
    state: NavigationBarState,
    modifier: Modifier = Modifier,
    containerColor: Color = NavigationBarDefaults.containerColor,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    onClickItem: (NavigationBarItemState) -> Unit = { }
) {
    androidx.compose.material3.NavigationBar(
        modifier = modifier.testTag(state.testTag),
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        windowInsets = windowInsets
    ) {
        state.items.forEach { item ->
            NavigationBarItem(
                selected = item.selected,
                icon = { item.icon?.let { Icon(it) } },
                label = { item.label?.let { Text(it) } },
                onClick = { onClickItem(item) }
            )
        }
    }
}

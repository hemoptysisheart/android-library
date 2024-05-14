package com.github.hemoptysisheart.ui.compose.scaffold

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.hemoptysisheart.ui.compose.Text
import com.github.hemoptysisheart.ui.state.scaffold.TitleTopBarState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TitleTopBar(state: TitleTopBarState, modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(state = state.title, modifier = Modifier.fillMaxWidth()) },
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
fun TitleTopBarPreview() {
    TitleTopBar(TitleTopBarState("Title top bar."), Modifier.fillMaxWidth())
}

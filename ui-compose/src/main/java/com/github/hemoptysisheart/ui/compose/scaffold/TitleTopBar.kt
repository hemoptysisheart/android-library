package com.github.hemoptysisheart.ui.compose.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.github.hemoptysisheart.ui.compose.Text
import com.github.hemoptysisheart.ui.state.scaffold.TitleTopBarState

@Composable
fun TitleTopBar(state: TitleTopBarState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.testTag(state.testTag),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface {
            Text(state.title)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TitleTopBarPreview() {
    TitleTopBar(TitleTopBarState("Title top bar."), Modifier.fillMaxWidth())
}

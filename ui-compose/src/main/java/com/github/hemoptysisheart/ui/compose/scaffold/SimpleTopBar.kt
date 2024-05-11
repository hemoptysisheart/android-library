package com.github.hemoptysisheart.ui.compose.scaffold

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.github.hemoptysisheart.ui.compose.Text
import com.github.hemoptysisheart.ui.compose.preview.SimpleTopBarStateProvider
import com.github.hemoptysisheart.ui.state.scaffold.SimpleTopBarState

@Composable
fun SimpleTopBar(state: SimpleTopBarState, modifier: Modifier = Modifier, onClickBack: (() -> Unit)? = null) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { state.onClickBack(onClickBack) },
            enabled = state.enableBack
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }

        Spacer(modifier = Modifier.weight(1F))
        state.leadingIcon?.let { li ->
            Icon(painter = painterResource(li), contentDescription = null)
        }
        Text(state = state.title)
        state.trailingIcon?.let { ti ->
            Icon(painter = painterResource(ti), contentDescription = null)
        }
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Composable
@Preview(showBackground = true)
private fun SimpleTopBarPreview(@PreviewParameter(SimpleTopBarStateProvider::class) state: SimpleTopBarState) {
    SimpleTopBar(state, Modifier.fillMaxWidth())
}

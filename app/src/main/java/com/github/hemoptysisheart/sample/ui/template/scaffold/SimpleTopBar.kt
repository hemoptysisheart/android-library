package com.github.hemoptysisheart.sample.ui.template.scaffold

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.ui.compose.preview.SimpleTopBarStateProvider
import com.github.hemoptysisheart.ui.state.SimpleTopBarState


@Composable
fun SimpleTopBar(navController: NavHostController, state: SimpleTopBarState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }, enabled = state.enableBackward) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }

        Spacer(modifier = Modifier.weight(1F))
        state.leadingIcon?.let { li ->
            Icon(painter = painterResource(li), contentDescription = null)
        }
        Text(text = state.title, textAlign = TextAlign.Center)
        state.trailingIcon?.let { ti ->
            Icon(painter = painterResource(ti), contentDescription = null)
        }
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Composable
@Preview(showBackground = true)
private fun SimpleTopBarPreview(@PreviewParameter(SimpleTopBarStateProvider::class) state: SimpleTopBarState) {
    AndroidLibraryTheme {
        SimpleTopBar(rememberNavController(), state, Modifier.fillMaxWidth())
    }
}

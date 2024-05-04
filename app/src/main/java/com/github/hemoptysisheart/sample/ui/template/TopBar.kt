package com.github.hemoptysisheart.sample.ui.template

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme

@Composable
fun TopBar() {
    Text(text = "top bar", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
}

@Composable
@Preview(showBackground = true)
private fun TopBarPreview() {
    AndroidLibraryTheme {
        TopBar()
    }
}

package com.github.hemoptysisheart.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.ui.compose.TextField
import com.github.hemoptysisheart.ui.state.SimpleTextFieldState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidLibraryTheme {
                Content()
            }
        }
    }
}

@Composable
fun Content() {
    TextField(state = SimpleTextFieldState(text = "", onChange = {}))
}

@Composable
@Preview
fun PreviewContent() {
    AndroidLibraryTheme {
        Content()
    }
}

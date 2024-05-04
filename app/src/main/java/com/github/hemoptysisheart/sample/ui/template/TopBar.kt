package com.github.hemoptysisheart.sample.ui.template

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme

@Composable
fun TopBar(navController: NavHostController) {
    Log.v(TAG, "#TopBar args : navController=$navController")

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        Text(text = "top bar", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    }
}

@Composable
@Preview(showBackground = true)
private fun TopBarPreview() {
    AndroidLibraryTheme {
        TopBar(rememberNavController())
    }
}

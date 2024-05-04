package com.github.hemoptysisheart.sample.ui.template.scaffold

import android.util.Log
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme

@Composable
fun BottomBar(navController: NavHostController) {
    Log.v(TAG, "#BottomBar args : navController=$navController")

    BottomAppBar(modifier = Modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = "select-size" == navController.currentDestination?.route,
            onClick = {
                if ("select-size" != navController.currentDestination?.route) {
                    navController.navigate("select-size") {
                        popUpTo(navController.graph.findStartDestination().route!!) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            icon = { Icon(imageVector = Default.PlayArrow, contentDescription = null) },
            label = { Text(text = "Maze") }
        )

        NavigationBarItem(
            selected = "history" == navController.currentDestination?.route,
            onClick = {
                if ("history" != navController.currentDestination?.route) {
                    navController.navigate("history") {
                        popUpTo(navController.graph.findStartDestination().route!!) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            icon = { Icon(imageVector = Default.History, contentDescription = null) },
            label = { Text(text = "History") }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun BottomBarPreview() {
    AndroidLibraryTheme {
        BottomBar(rememberNavController())
    }
}

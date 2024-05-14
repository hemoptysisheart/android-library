package com.github.hemoptysisheart.sample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.hemoptysisheart.sample.ui.navigation.SplashNavigator
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.sample.viewmodel.SplashViewModel
import com.github.hemoptysisheart.ui.navigation.compose.baseViewModel

@Composable
fun SplashPage(navigator: SplashNavigator, viewModel: SplashViewModel = baseViewModel()) {
    Log.v(TAG, "#SplashPage called.")

    var stay by remember { mutableStateOf(true) }

    val timeout by viewModel.timeout.collectAsStateWithLifecycle()

    SplashPageContent(
        timeout = timeout,
        onTimeout = {
            if (stay) {
                stay = false
                navigator.selectSize()
            }
        }
    )
}

/**
 * @param timeout Splash 화면 표시 시간이 끝났는지 여부.
 * @param onTimeout Splash 화면 표시 시간이 끝났을 때 호출할 함수.
 */
@Composable
private fun SplashPageContent(
    timeout: Boolean,
    onTimeout: () -> Unit = {}
) {
    if (timeout) {
        onTimeout()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "SplashPage")
    }
}

@Composable
@Preview(showSystemUi = true)
private fun SplashPagePreview() {
    AndroidLibraryTheme {
        SplashPageContent(false)
    }
}

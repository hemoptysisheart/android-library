package com.github.hemoptysisheart.ui.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.github.hemoptysisheart.viewmodel.BaseViewModel

/**
 * `@Composable`에서 [BaseViewModel]을 사용할 수 있도록 [hiltViewModel]을 확장한다.
 */
@Composable
inline fun <reified VM : BaseViewModel> baseViewModel(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    key: String? = null
): VM {
    val viewModel: VM = hiltViewModel(viewModelStoreOwner, key)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(viewModel) {
        lifecycle.addObserver(viewModel)
        onDispose {
            lifecycle.removeObserver(viewModel)
        }
    }
    return viewModel
}

/**
 * `@Composable`에서 [BaseViewModel]을 사용할 수 있도록 [hiltViewModel]을 확장한다.
 */
@Composable
inline fun <reified VM : BaseViewModel, reified VMF> baseViewModel(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    key: String? = null,
    noinline creationCallback: (VMF) -> VM
): VM {
    val viewModel: VM = hiltViewModel(viewModelStoreOwner, key, creationCallback)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(viewModel) {
        lifecycle.addObserver(viewModel)
        onDispose {
            lifecycle.removeObserver(viewModel)
        }
    }
    return viewModel
}

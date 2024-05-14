package com.github.hemoptysisheart.sample.viewmodel

import android.util.Log
import androidx.compose.ui.text.style.TextAlign
import com.github.hemoptysisheart.sample.R.drawable
import com.github.hemoptysisheart.sample.model.FallbackViewModelScopeExceptionHandler
import com.github.hemoptysisheart.sample.ui.navigation.HistoryNavigator
import com.github.hemoptysisheart.sample.ui.navigation.SelectSizeNavigator
import com.github.hemoptysisheart.ui.state.IconState
import com.github.hemoptysisheart.ui.state.InteractionImpact.VISIBLE
import com.github.hemoptysisheart.ui.state.TextState
import com.github.hemoptysisheart.ui.state.scaffold.NavigationBarItemState
import com.github.hemoptysisheart.ui.state.scaffold.NavigationBarState
import com.github.hemoptysisheart.ui.state.scaffold.TitleTopBarState
import com.github.hemoptysisheart.viewmodel.ScaffoldContentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    fallbackViewModelScopeExceptionHandler: FallbackViewModelScopeExceptionHandler
) : ScaffoldContentViewModel<TitleTopBarState, NavigationBarState>(
    tag = "HistoryViewModel",
    fallbackCoroutineExceptionHandler = fallbackViewModelScopeExceptionHandler,
    topBar = TitleTopBarState(TextState(text = "History", textAlign = TextAlign.Center)),
    bottomBar = NavigationBarState(
        listOf(
            NavigationBarItemState(
                destination = SelectSizeNavigator.id,
                selected = false,
                icon = IconState(resourceId = drawable.ic_launcher_foreground),
                label = TextState(text = "Maze")
            ),
            NavigationBarItemState(
                destination = HistoryNavigator.id,
                selected = true,
                icon = IconState(resourceId = drawable.ic_launcher_background),
                label = TextState(text = "History")
            )
        )
    )
) {
    /**
     * [androidx.lifecycle.ViewModel]의 [CoroutineExceptionHandler] 테스트용 메서드.
     */
    fun onClickError() {
        Log.d(tag, "#onClickError called.")
        launch(impact = VISIBLE) {
            delay(1_000L)
            throw RuntimeException("Test exception")
        }
    }

    override fun toString() = listOf<String>(
        super.toString()
    ).joinToString(", ", "$tag(", ")")
}

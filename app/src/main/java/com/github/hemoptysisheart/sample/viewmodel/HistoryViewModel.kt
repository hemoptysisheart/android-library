package com.github.hemoptysisheart.sample.viewmodel

import com.github.hemoptysisheart.ui.state.SimpleTopBarState
import com.github.hemoptysisheart.viewmodel.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel(
    tag = "HistoryViewModel",
    topBar = SimpleTopBarState(enableBackward = false, title = "History")
) {
    override fun toString() = listOf<String>(
    ).joinToString(", ", "$tag(${super.toString()}", ")")
}

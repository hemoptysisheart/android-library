package com.github.hemoptysisheart.sample.viewmodel

import com.github.hemoptysisheart.viewmodel.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel("HistoryViewModel") {
    override fun toString() = listOf<String>(
    ).joinToString(", ", "HistoryViewModel(", ")")
}

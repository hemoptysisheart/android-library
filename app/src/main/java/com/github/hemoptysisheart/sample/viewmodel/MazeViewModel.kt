package com.github.hemoptysisheart.sample.viewmodel

import com.github.hemoptysisheart.viewmodel.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MazeViewModel @Inject constructor() : ViewModel("MazeViewModel") {
    override fun toString() = listOf<String>(
    ).joinToString(", ", "$tag(${super.toString()}", ")")
}

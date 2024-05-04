package com.github.hemoptysisheart.ui.state

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable

@Stable
data class SimpleTopBarState(
    val enableBackward: Boolean,
    val title: String,
    @DrawableRes val leadingIcon: Int? = null,
    @DrawableRes val trailingIcon: Int? = null
) : TopBarState

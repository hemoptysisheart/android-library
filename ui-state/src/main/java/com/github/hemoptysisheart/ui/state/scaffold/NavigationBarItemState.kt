package com.github.hemoptysisheart.ui.state.scaffold

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.github.hemoptysisheart.ui.state.State
import java.util.UUID

/**
 * [androidx.compose.material3.NavigationBarItem]의 상태 홀더.
 */
@Stable
@SuppressLint("SupportAnnotationUsage")
data class NavigationBarItemState(
    /**
     * 선택 여부.
     * @see androidx.compose.material3.NavigationBarItem `selected`
     */
    val selected: Boolean,
    @DrawableRes val icon: Int? = null,
    @StringRes val label: String? = null,
    override val key: UUID = UUID.randomUUID(),
    override val testTag: String = key.toString(),
    private val _onClick: () -> Unit
) : State {
    fun onClick() = _onClick()
}

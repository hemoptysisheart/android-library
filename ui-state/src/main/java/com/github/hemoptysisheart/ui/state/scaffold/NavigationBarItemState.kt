package com.github.hemoptysisheart.ui.state.scaffold

import androidx.compose.runtime.Stable
import com.github.hemoptysisheart.ui.state.IconState
import com.github.hemoptysisheart.ui.state.State
import com.github.hemoptysisheart.ui.state.TextState
import java.util.UUID

/**
 * [androidx.compose.material3.NavigationBarItem]의 상태 홀더.
 */
@Stable
class NavigationBarItemState(
    /**
     * 백스택 엔트리 ID.
     *
     * @see com.github.hemoptysisheart.ui.navigation.destination.Destination.id
     */
    val destination: String,
    /**
     * 선택 여부.
     * @see androidx.compose.material3.NavigationBarItem `selected`
     */
    val selected: Boolean,
    /**
     * 아이콘. `null`이면 비표시.
     */
    val icon: IconState? = null,
    /**
     * 레이블. `null`이면 비표시.
     */
    val label: TextState? = null,
    override val key: UUID = UUID.randomUUID(),
    override val testTag: String = key.toString()
) : State {
    fun copy(
        selected: Boolean = this.selected,
        icon: IconState? = this.icon,
        label: TextState? = this.label
    ) = NavigationBarItemState(
        destination = destination,
        selected = selected,
        icon = icon,
        label = label,
        key = key,
        testTag = testTag
    )

    override fun equals(other: Any?) = this === other || (
            other is NavigationBarItemState &&
                    destination == other.destination &&
                    selected == other.selected &&
                    icon == other.icon &&
                    label == other.label &&
                    key == other.key &&
                    testTag == other.testTag
            )

    override fun hashCode(): Int {
        var result = destination.hashCode()
        result = 31 * result + selected.hashCode()
        result = 31 * result + (icon?.hashCode() ?: 0)
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + key.hashCode()
        result = 31 * result + testTag.hashCode()
        return result
    }

    override fun toString() = listOfNotNull(
        "destination='$destination'",
        "selected=$selected",
        icon?.let { "icon=$it" },
        label?.let { "label=$it" },
        "key=$key",
        "testTag='$testTag'"
    ).joinToString(", ", "NavigationBarItemState(", ")")
}

package com.github.hemoptysisheart.ui.state.scaffold

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import java.util.UUID

@Stable
data class SimpleTopBarState(
    val enableBack: Boolean,
    val title: String,
    @DrawableRes val leadingIcon: Int? = null,
    @DrawableRes val trailingIcon: Int? = null,
    override val key: UUID = UUID.randomUUID(),
    override val testTag: String = key.toString(),
    private val _back: (Boolean) -> Unit = {},
) : TopBarState {
    fun back() = _back(enableBack)

    fun copy(
        enableBack: Boolean = this.enableBack,
        title: String = this.title,
        leadingIcon: Int? = this.leadingIcon,
        trailingIcon: Int? = this.trailingIcon
    ) = SimpleTopBarState(
        enableBack = enableBack,
        title = title,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        key = key,
        testTag = testTag,
        _back = _back
    )

    override fun equals(other: Any?) = this === other || (
            other is SimpleTopBarState &&
                    enableBack == other.enableBack &&
                    title == other.title &&
                    leadingIcon == other.leadingIcon &&
                    trailingIcon == other.trailingIcon &&
                    key == other.key &&
                    testTag == other.testTag &&
                    _back == other._back
            )

    override fun hashCode(): Int {
        var result = enableBack.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (leadingIcon ?: 0)
        result = 31 * result + (trailingIcon ?: 0)
        result = 31 * result + key.hashCode()
        result = 31 * result + testTag.hashCode()
        result = 31 * result + _back.hashCode()
        return result
    }

    override fun toString() = listOf(
        "enableBack=$enableBack",
        "title='$title'",
        "leadingIcon=$leadingIcon",
        "trailingIcon=$trailingIcon",
        "key=$key",
        "testTag='$testTag'"
    ).joinToString(", ", "SimpleTopBarState(", ")")
}

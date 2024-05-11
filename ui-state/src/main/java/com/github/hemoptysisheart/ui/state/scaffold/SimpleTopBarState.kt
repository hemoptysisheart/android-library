package com.github.hemoptysisheart.ui.state.scaffold

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.github.hemoptysisheart.ui.state.TextState
import java.util.UUID

@Stable
data class SimpleTopBarState(
    val enableBack: Boolean,
    val title: TextState,
    @DrawableRes val leadingIcon: Int? = null,
    @DrawableRes val trailingIcon: Int? = null,
    override val key: UUID = UUID.randomUUID(),
    override val testTag: String = key.toString(),
    private val _onClickBack: (Boolean, (() -> Unit)?) -> Unit = { enable, callback ->
        Log.d(testTag, "#onClickBack args : enable=$enable, callback=$callback")
        if (enable) callback?.invoke()
    }
) : TopBarState {
    constructor(
        enableBack: Boolean,
        title: String,
        @DrawableRes leadingIcon: Int? = null,
        @DrawableRes trailingIcon: Int? = null,
        key: UUID = UUID.randomUUID(),
        testTag: String = key.toString(),
        onClickBack: (enable: Boolean, callback: (() -> Unit)?) -> Unit = { enable, callback ->
            Log.d(testTag, "#onClickBack args : enable=$enable, callback=$callback")
            if (enable) callback?.invoke()
        }
    ) : this(
        enableBack = enableBack,
        title = TextState(title),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        key = key,
        testTag = testTag,
        _onClickBack = onClickBack
    )

    fun onClickBack(callback: (() -> Unit)?) = _onClickBack(enableBack, callback)

    fun copy(
        enableBack: Boolean = this.enableBack,
        title: TextState = this.title,
        leadingIcon: Int? = this.leadingIcon,
        trailingIcon: Int? = this.trailingIcon
    ) = SimpleTopBarState(
        enableBack = enableBack,
        title = title,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        key = key,
        testTag = testTag,
        _onClickBack = _onClickBack
    )

    override fun equals(other: Any?) = this === other || (
            other is SimpleTopBarState &&
                    enableBack == other.enableBack &&
                    title == other.title &&
                    leadingIcon == other.leadingIcon &&
                    trailingIcon == other.trailingIcon &&
                    key == other.key &&
                    testTag == other.testTag &&
                    _onClickBack == other._onClickBack
            )

    override fun hashCode(): Int {
        var result = enableBack.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (leadingIcon ?: 0)
        result = 31 * result + (trailingIcon ?: 0)
        result = 31 * result + key.hashCode()
        result = 31 * result + testTag.hashCode()
        result = 31 * result + _onClickBack.hashCode()
        return result
    }

    override fun toString() = listOf(
        "enableBack=$enableBack",
        "title='$title'",
        "leadingIcon=$leadingIcon",
        "trailingIcon=$trailingIcon",
        "key=$key",
        "testTag='$testTag'",
        "onClickBack=$_onClickBack"
    ).joinToString(", ", "SimpleTopBarState(", ")")
}

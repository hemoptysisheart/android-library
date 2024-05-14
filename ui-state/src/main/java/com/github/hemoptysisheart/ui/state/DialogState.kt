package com.github.hemoptysisheart.ui.state

import androidx.compose.ui.window.DialogProperties

/**
 * [androidx.compose.ui.window.Dialog]용 상태 홀더.
 */
interface DialogState : State {
    val properties: DialogProperties
}

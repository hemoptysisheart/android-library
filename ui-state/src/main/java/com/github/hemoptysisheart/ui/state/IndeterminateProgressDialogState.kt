package com.github.hemoptysisheart.ui.state

import androidx.compose.ui.window.DialogProperties
import java.util.UUID

object IndeterminateProgressDialogState : DialogState {
    override val properties: DialogProperties = DialogProperties()

    override val key: UUID = UUID.randomUUID()

    override val testTag = "IndeterminateProgressDialog"
}

package com.github.hemoptysisheart.ui.state

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID

/**
 * [androidx.compose.material3.Icon]의 상태 홀더.
 */
@Stable
class IconState(
    @DrawableRes val resourceId: Int? = null,
    val imageVector: ImageVector? = null,
    val bitmap: ImageBitmap? = null,
    val painter: Painter? = null,
    val contentDescription: String? = null,
    val tint: Color = Color.Unspecified,
    override val key: UUID = UUID.randomUUID(),
    override val testTag: String = key.toString()
) : State {
    init {
        require((null != resourceId) xor (null != imageVector) xor (null != bitmap) xor (null != painter)) {
            "Only one of drawable, imageVector, bitmap, painter should be set."
        }
    }

    fun copy(
        resourceId: Int? = null,
        imageVector: ImageVector? = null,
        bitmap: ImageBitmap? = null,
        painter: Painter? = null,
        contentDescription: String? = null,
        tint: Color = Color.Unspecified,
    ) = IconState(
        resourceId = resourceId,
        imageVector = imageVector,
        bitmap = bitmap,
        painter = painter,
        contentDescription = contentDescription,
        tint = tint,
        key = key,
        testTag = testTag
    )

    override fun equals(other: Any?) = this === other || (
            other is IconState &&
                    resourceId == other.resourceId &&
                    imageVector == other.imageVector &&
                    bitmap == other.bitmap &&
                    painter == other.painter &&
                    contentDescription == other.contentDescription &&
                    tint == other.tint &&
                    key == other.key &&
                    testTag == other.testTag
            )

    override fun hashCode(): Int {
        var result = resourceId ?: 0
        result = 31 * result + (imageVector?.hashCode() ?: 0)
        result = 31 * result + (bitmap?.hashCode() ?: 0)
        result = 31 * result + (painter?.hashCode() ?: 0)
        result = 31 * result + (contentDescription?.hashCode() ?: 0)
        result = 31 * result + tint.hashCode()
        result = 31 * result + key.hashCode()
        result = 31 * result + testTag.hashCode()
        return result
    }

    override fun toString() = listOfNotNull(
        resourceId?.let { "resourceId=$it" },
        imageVector?.let { "imageVector=$it" },
        bitmap?.let { "bitmap=$it" },
        painter?.let { "painter=$it" },
        "contentDescription=$contentDescription",
        "tint=$tint",
        "key=$key",
        "testTag='$testTag'"
    ).joinToString(separator = ", ", prefix = "IconState(", postfix = ")")
}

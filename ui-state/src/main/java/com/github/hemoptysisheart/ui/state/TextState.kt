package com.github.hemoptysisheart.ui.state

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import java.util.UUID

/**
 * [androidx.compose.material3.Text]용 상태 홀더.
 */
@Immutable
class TextState(
    @StringRes val resourceId: Int? = null,
    val text: String? = null,
    val color: Color = Color.Unspecified,
    val fontSize: TextUnit = TextUnit.Unspecified,
    val fontStyle: FontStyle? = null,
    val fontWeight: FontWeight? = null,
    val fontFamily: FontFamily? = null,
    val letterSpacing: TextUnit = TextUnit.Unspecified,
    val textDecoration: TextDecoration? = null,
    val textAlign: TextAlign? = null,
    val lineHeight: TextUnit = TextUnit.Unspecified,
    val overflow: TextOverflow = TextOverflow.Clip,
    val softWrap: Boolean = true,
    val textLines: TextLines = MultiLines(),
    val style: TextStyle? = null,
    override val key: UUID = UUID.randomUUID(),
    override val testTag: String = key.toString()
) : State {
    fun copy(
        resourceId: Int? = null,
        text: String? = null,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontStyle: FontStyle? = null,
        fontWeight: FontWeight? = null,
        fontFamily: FontFamily? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        textLines: TextLines = MultiLines(),
        style: TextStyle? = null
    ) = TextState(
        resourceId = resourceId,
        text = text,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        textLines = textLines,
        style = style,
        key = key,
        testTag = testTag
    )

    override fun equals(other: Any?) = this === other || (
            other is TextState &&
                    resourceId == other.resourceId &&
                    text == other.text &&
                    key == other.key &&
                    testTag == other.testTag &&
                    color == other.color &&
                    fontSize == other.fontSize &&
                    fontStyle == other.fontStyle &&
                    fontWeight == other.fontWeight &&
                    fontFamily == other.fontFamily &&
                    letterSpacing == other.letterSpacing &&
                    textDecoration == other.textDecoration &&
                    textAlign == other.textAlign &&
                    lineHeight == other.lineHeight &&
                    overflow == other.overflow &&
                    softWrap == other.softWrap &&
                    textLines == other.textLines &&
                    style == other.style
            )

    override fun hashCode(): Int {
        var result = resourceId.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + key.hashCode()
        result = 31 * result + testTag.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + fontSize.hashCode()
        result = 31 * result + (fontStyle?.hashCode() ?: 0)
        result = 31 * result + (fontWeight?.hashCode() ?: 0)
        result = 31 * result + (fontFamily?.hashCode() ?: 0)
        result = 31 * result + letterSpacing.hashCode()
        result = 31 * result + (textDecoration?.hashCode() ?: 0)
        result = 31 * result + (textAlign?.hashCode() ?: 0)
        result = 31 * result + lineHeight.hashCode()
        result = 31 * result + overflow.hashCode()
        result = 31 * result + softWrap.hashCode()
        result = 31 * result + textLines.hashCode()
        result = 31 * result + (style?.hashCode() ?: 0)
        return result
    }

    override fun toString() = listOfNotNull(
        resourceId?.let { "resourceId=$it" },
        text?.let { "text='$it'" },
        "key=$key",
        "testTag='$testTag'",
        "color=$color",
        "fontSize=$fontSize",
        "fontStyle=$fontStyle",
        "fontWeight=$fontWeight",
        "fontFamily=$fontFamily",
        "letterSpacing=$letterSpacing",
        "textDecoration=$textDecoration",
        "textAlign=$textAlign",
        "lineHeight=$lineHeight",
        "overflow=$overflow",
        "softWrap=$softWrap",
        "textLines=$textLines",
        "style=$style"
    ).joinToString(", ", "TextState(", ")")
}

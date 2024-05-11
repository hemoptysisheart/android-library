package com.github.hemoptysisheart.ui.state

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
open class TextState(
    val text: String,
    override val key: UUID = UUID.randomUUID(),
    override val testTag: String = key.toString(),
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
    val style: TextStyle? = null
) : State {
    override fun equals(other: Any?) = this === other || (
            other is TextState &&
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
        var result = text.hashCode()
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

    override fun toString() = listOf(
        "text='$text'",
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

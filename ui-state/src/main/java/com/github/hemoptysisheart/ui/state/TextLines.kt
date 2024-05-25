package com.github.hemoptysisheart.ui.state

sealed class TextLines {
    @Suppress("ConstPropertyName")
    data object Default : TextLines() {
        const val minLines: Int = 1

        const val maxLines: Int = Int.MAX_VALUE
    }
}

data object SingleLine : TextLines()

data class MultiLines(
    val minLines: Int = 1,
    val maxLines: Int = Int.MAX_VALUE
) : TextLines() {
    init {
        require(minLines > 0) { "minLines must be greater than 0 : minLines=$minLines" }
        require(maxLines >= minLines) {
            "maxLines must be greater than or equal to minLines : maxLines=$maxLines, minLines=$minLines"
        }
    }
}

package com.github.hemoptysisheart.sample.domain

data class Cell(
    val x: Int,
    val y: Int
) {
    internal var group: Group? = null

    init {
        require(x >= 0) { "x must be greater than or equal to 0 : x=$x" }
        require(y >= 0) { "y must be greater than or equal to 0 : y=$y" }
    }

    override fun toString() = listOf(
        "x=$x",
        "y=$y",
        "group=${group?.id}"
    ).joinToString(", ", "Cell(", ")")
}

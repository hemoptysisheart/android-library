package com.github.hemoptysisheart.sample.domain

data class Cell(
    val x: Int,
    val y: Int
) : Comparable<Cell> {
    internal var group: Group? = null

    init {
        require(x >= 0) { "x must be greater than or equal to 0 : x=$x" }
        require(y >= 0) { "y must be greater than or equal to 0 : y=$y" }
    }

    override fun compareTo(other: Cell): Int {
        val score = x * x + y * y
        val otherScore = other.x * other.x + other.y * other.y
        var diff = score - otherScore
        if (0 != diff) {
            return diff
        }

        diff = y - other.y
        if (0 != diff) {
            return diff
        }

        return x - other.x
    }

    override fun toString() = listOf(
        "x=$x",
        "y=$y",
        "group=${group?.id}"
    ).joinToString(", ", "Cell(", ")")
}

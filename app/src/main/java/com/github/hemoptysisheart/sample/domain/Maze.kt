package com.github.hemoptysisheart.sample.domain

class Maze(
    val width: Int,
    val height: Int
) {
    override fun toString() = listOf(
        "width=$width",
        "height=$height"
    ).joinToString(", ", "Maze(", ")")
}

package com.github.hemoptysisheart.sample.domain

class Link(
    a: Cell,
    b: Cell
) : Comparable<Link> {
    val a: Cell = if (a < b) a else b
    val b: Cell = if (a < b) b else a

    init {
        require(a != b) { "a must not be equal to b : a=$a, b=$b" }
    }

    override fun compareTo(other: Link): Int {
        val diff = a.compareTo(other.a)
        if (0 != diff) {
            return diff
        }

        return b.compareTo(other.b)
    }

    override fun equals(other: Any?) = this === other || (
            other is Link &&
                    a == other.a &&
                    b == other.b
            )

    override fun hashCode(): Int {
        var result = a.hashCode()
        result = 31 * result + b.hashCode()
        return result
    }

    override fun toString() = "Link(a=$a, b=$b)"
}

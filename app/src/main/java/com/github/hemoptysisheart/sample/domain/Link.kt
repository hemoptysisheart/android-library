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

    /**
     * 링크의 반대쪽 셀을 반환한다.
     *
     * @param cell 셀
     * @return 링크의 반대쪽 셀을 반환한다. 주어진 셀이 링크에 포함되어 있지 않다면 `null`을 반환한다.
     */
    fun opposite(cell: Cell): Cell? = when (cell) {
        a ->
            b

        b ->
            a

        else ->
            null
    }

    fun contains(cell: Cell) = a == cell || b == cell

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

package com.github.hemoptysisheart.sample.domain

import com.github.hemoptysisheart.sample.domain.Direction.EAST
import com.github.hemoptysisheart.sample.domain.Direction.NORTH
import com.github.hemoptysisheart.sample.domain.Direction.SOUTH
import com.github.hemoptysisheart.sample.domain.Direction.WEST

data class Cell(
    val x: Int,
    val y: Int
) : Comparable<Cell> {
    internal var group: Group? = null

    init {
        require(x >= 0) { "x must be greater than or equal to 0 : x=$x" }
        require(y >= 0) { "y must be greater than or equal to 0 : y=$y" }
    }

    /**
     * 이 셀이 주어진 링크의 어느 방향에 있는지 반환한다.
     *
     * @param link 링크
     * @return 이 셀이 주어진 링크의 어느 방향에 있는지 반환한다. 주어진 링크에 이 셀이 포함되어 있지 않다면 `null`을 반환한다.
     */
    fun direction(link: Link): Direction? {
        val opposite = link.opposite(this)
        return if (null == opposite) {
            null
        } else {
            val dx = opposite.x - x
            val dy = opposite.y - y
            when {
                0 == dx && 1 == dy -> SOUTH
                0 == dx && -1 == dy -> NORTH
                1 == dx && 0 == dy -> EAST
                -1 == dx && 0 == dy -> WEST
                else -> null // 실행 불가능한 상태.
            }
        }
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

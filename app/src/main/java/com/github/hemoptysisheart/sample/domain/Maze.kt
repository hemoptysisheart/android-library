package com.github.hemoptysisheart.sample.domain

class Maze(
    val width: Int,
    val height: Int
) {
    private val _cells = Array(width * height) { index ->
        Cell(index % width, index / width)
    }
    val cells: List<Cell>
        get() = _cells.toList()

    private val _links = mutableListOf<Link>()
    val links: List<Link>
        get() = _links.toList()

    val start: Cell
    val end: Cell

    init {
        val remainCells = _cells.toMutableSet()
        start = _cells[0, 0]
        end = _cells[width - 1, height - 1]
        remainCells.remove(start)
        remainCells.remove(end)

        val groups = mutableListOf(Group(start), Group(end))
        while (remainCells.isNotEmpty()) {
            val cell = remainCells.random()
            val neighbor = neighbors(cell)
                .shuffled()
                .firstOrNull { null != it.group }

            if (null == neighbor) {
                val group = Group(cell)
                groups.add(group)
            } else {
                _links.add(Link(cell, neighbor))
                val group = groups.find { it.contains(neighbor) }
                group!!.add(cell)
            }
            remainCells.remove(cell)

            // TODO 그룹 합치기
        }
    }

    private fun neighbors(cell: Cell) = listOf(
        cell.x - 1 to cell.y,
        cell.x + 1 to cell.y,
        cell.x to cell.y - 1,
        cell.x to cell.y + 1
    ).mapNotNull { (x, y) ->
        if (x in 0 until width && y in 0 until height) {
            _cells[x, y]
        } else {
            null
        }
    }

    /**
     * 좌표값으로 배열 요소를 반환한다.
     */
    private operator fun <E> Array<E>.get(x: Int, y: Int) = _cells[index(x, y)]

    /**
     * 좌표값으로 배열 인덱스를 계산한다.
     */
    private fun index(x: Int, y: Int) = y * width + x

    override fun toString() = listOf(
        "width=$width",
        "height=$height",
        "start=$start",
        "end=$end",
        "cells=$cells",
        "links=$links"
    ).joinToString(", ", "Maze(", ")")
}

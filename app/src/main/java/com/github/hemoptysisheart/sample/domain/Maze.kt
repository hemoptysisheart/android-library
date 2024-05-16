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
            val neighborCells = neighbors(cell)
                .filter { null != it.group }

            if (neighborCells.isEmpty()) {
                groups.add(Group(cell))
            } else {
                neighborCells.forEach { neighbor ->
                    val g1 = neighbor.group
                    val g2 = cell.group
                    if (g1 == g2) {
                        return@forEach
                    }

                    val merged = neighbor.group!!.cells +
                            (cell.group?.cells ?: emptySet()) + setOf(cell)
                    val g3 = Group(*merged.toTypedArray())

                    groups.remove(g1)
                    groups.remove(g2)
                    groups.add(g3)
                    _links.add(Link(cell, neighbor))
                }
            }
            remainCells.remove(cell)
        }

        require(1 == groups.size) {
            "illegal group size : groups.size=${groups.size}, groups=$groups"
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

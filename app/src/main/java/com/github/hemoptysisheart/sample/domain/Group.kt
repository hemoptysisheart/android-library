package com.github.hemoptysisheart.sample.domain

import java.util.UUID

class Group(
    vararg cells: Cell
) {
    val id = UUID.randomUUID()

    private val _cells = cells.toMutableSet()
    val cells: Set<Cell> = _cells

    init {
        cells.forEach { it.group = this }
    }

    fun contains(cell: Cell) = _cells.contains(cell)

    fun add(cell: Cell): Boolean {
        cell.group = this
        return _cells.add(cell)
    }

    override fun toString() = listOf(
        "id=$id",
        "cells=$_cells"
    ).joinToString(", ", "Group(", ")")
}
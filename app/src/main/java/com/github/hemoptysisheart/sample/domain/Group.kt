package com.github.hemoptysisheart.sample.domain

class Group(
    vararg cells: Cell
) {
    companion object {
        private var idCounter = 0
    }

    val id = idCounter++

    val cells: Set<Cell> = cells.toSet()

    init {
        cells.forEach { it.group = this }
    }

    fun contains(cell: Cell) = cells.contains(cell)

    operator fun plus(other: Group): Group {
        val list = cells.toMutableList()
        list.addAll(other.cells)
        val merged = Group(*list.toTypedArray())
        return merged
    }

    override fun toString() = listOf(
        "id=$id",
        "cells=$cells"
    ).joinToString(", ", "Group(", ")")
}
package com.github.hemoptysisheart.sample.ui.organism

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.hemoptysisheart.sample.domain.Cell
import com.github.hemoptysisheart.sample.domain.Direction
import com.github.hemoptysisheart.sample.domain.Direction.EAST
import com.github.hemoptysisheart.sample.domain.Direction.NORTH
import com.github.hemoptysisheart.sample.domain.Direction.SOUTH
import com.github.hemoptysisheart.sample.domain.Direction.WEST
import com.github.hemoptysisheart.sample.ui.molecule.Pillar
import com.github.hemoptysisheart.sample.ui.molecule.Wall
import com.github.hemoptysisheart.sample.ui.state.CellState
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme

@Composable
fun Cell(cell: CellState) {
    Log.v(TAG, "#Cell args : cell=$cell")

    val openWalls = remember(cell) { cell.openWalls }
    ConstraintLayout(modifier = Modifier.size(CELL_SIZE.dp)) {
        val (westNorth, northEast, eastSouth, southWest, westWall, northWall, eastWall, southWall) = createRefs()

        Pillar(constraint = westNorth) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }
        Pillar(constraint = northEast) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        }
        Pillar(constraint = eastSouth) {
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
        Pillar(constraint = southWest) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
        }

        if (!openWalls.contains(WEST)) {
            Wall(vertical = true, constraint = westWall) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        }
        if (!openWalls.contains(NORTH)) {
            Wall(vertical = false, constraint = northWall) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        }
        if (!openWalls.contains(EAST)) {
            Wall(vertical = true, constraint = eastWall) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
        }
        if (!openWalls.contains(SOUTH)) {
            Wall(vertical = false, constraint = southWall) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        }
    }
}

private class CellProvider : PreviewParameterProvider<CellState> {
    override val values: Sequence<CellState> = sequenceOf(
        // 0
        CellState(Cell(1, 1), emptyList()),

        // 1
        CellState(Cell(1, 1), listOf(WEST)),
        CellState(Cell(1, 1), listOf(NORTH)),
        CellState(Cell(1, 1), listOf(EAST)),
        CellState(Cell(1, 1), listOf(SOUTH)),

        // 2
        CellState(Cell(1, 1), listOf(WEST, NORTH)),
        CellState(Cell(1, 1), listOf(WEST, EAST)),
        CellState(Cell(1, 1), listOf(WEST, SOUTH)),
        CellState(Cell(1, 1), listOf(NORTH, EAST)),
        CellState(Cell(1, 1), listOf(NORTH, SOUTH)),
        CellState(Cell(1, 1), listOf(EAST, SOUTH)),

        // 3
        CellState(Cell(1, 1), listOf(WEST, NORTH, EAST)),
        CellState(Cell(1, 1), listOf(NORTH, EAST, SOUTH)),
        CellState(Cell(1, 1), listOf(EAST, SOUTH, WEST)),
        CellState(Cell(1, 1), listOf(SOUTH, WEST, NORTH)),

        // 4
        CellState(Cell(1, 1), Direction.entries.toList())
    )
}

@Composable
@Preview(showBackground = true)
private fun CellPreview(@PreviewParameter(CellProvider::class) cell: CellState) {
    AndroidLibraryTheme {
        Cell(cell)
    }
}

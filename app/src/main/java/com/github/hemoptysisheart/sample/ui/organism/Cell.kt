package com.github.hemoptysisheart.sample.ui.organism

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Start
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.hemoptysisheart.sample.domain.Direction
import com.github.hemoptysisheart.sample.domain.Direction.EAST
import com.github.hemoptysisheart.sample.domain.Direction.NORTH
import com.github.hemoptysisheart.sample.domain.Direction.SOUTH
import com.github.hemoptysisheart.sample.domain.Direction.WEST
import com.github.hemoptysisheart.sample.ui.molecule.Pillar
import com.github.hemoptysisheart.sample.ui.molecule.Wall
import com.github.hemoptysisheart.sample.ui.state.CellState
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme
import com.github.hemoptysisheart.ui.compose.preview.PreviewComponentNoText

@Composable
fun Cell(cell: CellState, onClick: (CellState) -> Unit = {}) {
    Log.v(TAG, "#Cell args : cell=$cell")

    val openWalls = remember(cell) { cell.openWalls }
    ConstraintLayout(modifier = Modifier
        .size(CELL_SIZE.dp)
        .background(MaterialTheme.colorScheme.background)
        .clickable { onClick(cell) }) {
        val (westNorth, northEast, eastSouth, southWest, westWall, northWall, eastWall, southWall, center) = createRefs()

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

        when {
            cell.start ->
                Icon(
                    imageVector = Icons.Default.Start,
                    contentDescription = "start",
                    modifier = Modifier.constrainAs(center) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    tint = Color.Red
                )

            cell.end ->
                Icon(
                    imageVector = Icons.Default.Flag,
                    contentDescription = "end",
                    modifier = Modifier.constrainAs(center) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    tint = Color.Red
                )

            cell.progress ->
                Icon(
                    imageVector = Icons.Default.Circle,
                    contentDescription = "progress",
                    modifier = Modifier.constrainAs(center) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    tint = Color.Red
                )
        }
    }
}

private class CellProvider : PreviewParameterProvider<CellState> {
    override val values: Sequence<CellState> = sequenceOf(
        CellState(x = 1, y = 1, start = true),
        CellState(x = 1, y = 1, end = true),
        CellState(x = 1, y = 1, progress = true),

        CellState(x = 1, y = 1, openWalls = Direction.entries.toList(), start = true),
        CellState(x = 1, y = 1, openWalls = Direction.entries.toList(), end = true),
        CellState(x = 1, y = 1, openWalls = Direction.entries.toList(), progress = true),

        // 0
        CellState(x = 1, y = 1),

        // 1
        CellState(x = 1, y = 1, openWalls = listOf(WEST)),
        CellState(x = 1, y = 1, openWalls = listOf(NORTH)),
        CellState(x = 1, y = 1, openWalls = listOf(EAST)),
        CellState(x = 1, y = 1, openWalls = listOf(SOUTH)),

        // 2
        CellState(x = 1, y = 1, openWalls = listOf(WEST, NORTH)),
        CellState(x = 1, y = 1, openWalls = listOf(WEST, EAST)),
        CellState(x = 1, y = 1, openWalls = listOf(WEST, SOUTH)),
        CellState(x = 1, y = 1, openWalls = listOf(NORTH, EAST)),
        CellState(x = 1, y = 1, openWalls = listOf(NORTH, SOUTH)),
        CellState(x = 1, y = 1, openWalls = listOf(EAST, SOUTH)),

        // 3
        CellState(x = 1, y = 1, openWalls = listOf(WEST, NORTH, EAST)),
        CellState(x = 1, y = 1, openWalls = listOf(NORTH, EAST, SOUTH)),
        CellState(x = 1, y = 1, openWalls = listOf(EAST, SOUTH, WEST)),
        CellState(x = 1, y = 1, openWalls = listOf(SOUTH, WEST, NORTH)),

        // 4
        CellState(x = 1, y = 1, openWalls = Direction.entries.toList())
    )
}

@Composable
@PreviewComponentNoText
private fun CellPreview(@PreviewParameter(CellProvider::class) cell: CellState) {
    AndroidLibraryTheme {
        Cell(cell)
    }
}

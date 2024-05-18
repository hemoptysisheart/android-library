package com.github.hemoptysisheart.sample.ui.organism

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.github.hemoptysisheart.sample.domain.Cell
import com.github.hemoptysisheart.sample.domain.Direction
import com.github.hemoptysisheart.sample.domain.Direction.EAST
import com.github.hemoptysisheart.sample.domain.Direction.NORTH
import com.github.hemoptysisheart.sample.domain.Direction.SOUTH
import com.github.hemoptysisheart.sample.domain.Direction.WEST
import com.github.hemoptysisheart.sample.ui.state.CellState
import com.github.hemoptysisheart.sample.ui.theme.AndroidLibraryTheme

const val CELL_SIZE = 60
const val PILLAR_SIZE = 10
const val WALL_THICKNESS = 5

@Composable
fun Cell(cell: CellState) {
    Log.v(TAG, "#Cell args : cell=$cell")

    val openWalls = remember(cell) { cell.openWalls }
    Column(
        modifier = Modifier
            .size(CELL_SIZE.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(modifier = Modifier.size(CELL_SIZE.dp, PILLAR_SIZE.dp), verticalAlignment = Alignment.Top) {
            Box(
                modifier = Modifier
                    .size(PILLAR_SIZE.dp)
                    .background(Color.Black)
            )
            val color = if (openWalls.contains(NORTH)) {
                Color.Transparent
            } else {
                Color.Black
            }
            Box(
                modifier = Modifier
                    .height(WALL_THICKNESS.dp)
                    .background(color)
                    .weight(1F)
            )
            Box(
                modifier = Modifier
                    .size(PILLAR_SIZE.dp)
                    .background(Color.Black)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height((CELL_SIZE - 2 * PILLAR_SIZE).dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            openWalls.contains(WEST).let {
                val color = if (it) {
                    Color.Transparent
                } else {
                    Color.Black
                }
                Box(
                    modifier = Modifier
                        .size(WALL_THICKNESS.dp, (CELL_SIZE - 2 * PILLAR_SIZE).dp)
                        .background(color)
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            Box(
                modifier = Modifier
                    .size((CELL_SIZE - 2 * PILLAR_SIZE).dp, WALL_THICKNESS.dp)
                    .weight(1F),
                contentAlignment = Alignment.Center
            ) {
            }
            Spacer(modifier = Modifier.weight(1F))
            openWalls.contains(EAST).let {
                val color = if (it) {
                    Color.Transparent
                } else {
                    Color.Black
                }
                Box(
                    modifier = Modifier
                        .size(WALL_THICKNESS.dp, (CELL_SIZE - 2 * PILLAR_SIZE).dp)
                        .background(color)
                )
            }
        }
        Row(modifier = Modifier.size(CELL_SIZE.dp, PILLAR_SIZE.dp), verticalAlignment = Alignment.Bottom) {
            Box(
                modifier = Modifier
                    .size(PILLAR_SIZE.dp)
                    .background(Color.Black)
            )
            val color = if (openWalls.contains(SOUTH)) {
                Color.Transparent
            } else {
                Color.Black
            }
            Box(
                modifier = Modifier
                    .height(WALL_THICKNESS.dp)
                    .background(color)
                    .weight(1F)
            )
            Box(
                modifier = Modifier
                    .size(PILLAR_SIZE.dp)
                    .background(Color.Black)
            )
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

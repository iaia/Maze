package model

import Cell
import Cells
import XY
import decorator.Decorator

class CellsImpl(
    override val width: Int,
    override val height: Int,
    private val decorator: Decorator,
) : Cells {
    private val cells: Array<Array<Cell?>> = Array(width) {
        arrayOfNulls(height)
    }
    private var wallCount: Int = 0
    override var start: Cell.Start? = null
        private set
    override var goal: Cell.Goal? = null
        private set

    override fun add(cell: Cell) {
        when (cells[cell.xy.y][cell.xy.x]) {
            is Cell.Start, is Cell.Goal -> return
            else -> Unit
        }
        when (cell) {
            is Cell.Wall -> {
                wallCount += 1
            }
            is Cell.Start -> {
                start = cell
            }
            is Cell.Goal -> {
                goal = cell
            }
            else -> Unit
        }
        cells[cell.xy.y][cell.xy.x] = cell
        decorator.sequentialOutput(cell)
    }

    override fun here(x: Int, y: Int): Cell? =
        try {
            cells[y][x]
        } catch (e: ArrayIndexOutOfBoundsException) {
            null
        }

    override fun here(xy: XY): Cell? =
        try {
            cells[xy.y][xy.x]
        } catch (e: ArrayIndexOutOfBoundsException) {
            null
        }

    override fun output() {
        decorator.fullOutput(cells)
    }
}

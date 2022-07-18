package model

import Cell
import Cells
import XY

class CellsImpl(
    override val width: Int,
    override val height: Int,
    private val sequentialOutput: Boolean,
) : Cells {
    private val cells: Array<Array<Cell?>> = Array(width) {
        arrayOfNulls(height)
    }
    private var wallCount: Int = 0
    override var start: Cell.Start? = null
        private set
    override var goal: Cell.Goal? = null
        private set
    override val procedure: MutableList<Cell> = mutableListOf()

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
        procedure.add(cell)
        if (sequentialOutput) {
            println(cell.toString())
        }
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
        cells.forEach { row ->
            row.forEach { cell ->
                // TODO: decoratorを作って外だしする
                print(
                    when (cell) {
                        is Cell.Start -> "s"
                        is Cell.Floor -> "_"
                        is Cell.Wall -> "x"
                        is Cell.Goal -> "g"
                        else -> "e"
                    }
                )
            }
            println()
        }
    }

    override fun outputProcedure() {
        procedure.forEach {
            println(it.toString())
        }
    }
}

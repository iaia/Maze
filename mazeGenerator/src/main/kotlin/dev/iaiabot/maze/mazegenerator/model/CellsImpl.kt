package dev.iaiabot.maze.mazegenerator.model

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Cells
import dev.iaiabot.maze.entity.Status
import dev.iaiabot.maze.entity.XY
import dev.iaiabot.maze.mazegenerator.decorator.Decorator

class CellsImpl(
    override val width: Int,
    override val height: Int,
    private val decorator: Decorator,
) : Cells {
    private val cells: Array<Array<Cell?>> = Array(height) {
        Array(width) { null }
    }
    private var wallCount: Int = 0
    private var status: Status = Status.INIT

    override var start: Cell.Start? = null
        private set
    override var goal: Cell.Goal? = null
        private set

    override fun add(cell: Cell) {
        when (cells[cell.y][cell.x]) {
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
        cells[cell.y][cell.x] = cell
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

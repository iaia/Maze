package dev.iaiabot.maze.entity

import dev.iaiabot.maze.entity.decorator.Decorator

// TODO: internal
class Cells(
    val width: Int,
    val height: Int,
    private val decorator: Decorator,
) {
    private val cells: Array<Array<Cell?>> = Array(height) {
        Array(width) { null }
    }
    private var wallCount: Int = 0

    var start: Cell.Start? = null
        private set
    var goal: Cell.Goal? = null
        private set

    fun add(cell: Cell) {
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

    fun here(x: Int, y: Int): Cell? =
        try {
            cells[y][x]
        } catch (e: ArrayIndexOutOfBoundsException) {
            null
        }

    fun here(xy: XY): Cell? =
        try {
            cells[xy.y][xy.x]
        } catch (e: ArrayIndexOutOfBoundsException) {
            null
        }

    fun output() {
        decorator.fullOutput(cells)
    }
}

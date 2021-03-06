package dev.iaiabot.maze.entity

import dev.iaiabot.maze.entity.decorator.Decorator

// TODO: internal
class Cells(
    val width: Int,
    val height: Int,
    private val decorator: Decorator,
) {
    private val cells: MutableList<MutableList<Cell>> = MutableList(height) { y ->
        MutableList(width) { x ->
            Cell.Empty(XY(x, y))
        }
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
        decorator.outputSequentialBuilding(cell)
    }

    fun here(x: Int, y: Int): Cell? =
        try {
            cells[y][x]
        } catch (e: IndexOutOfBoundsException) {
            null
        }

    fun here(xy: XY): Cell? =
        try {
            cells[xy.y][xy.x]
        } catch (e: IndexOutOfBoundsException) {
            null
        }

    fun output() {
        decorator.fullOutput(cells)
    }

    internal fun dump() = cells

    internal fun checkGoalAround() {
        if (
            cells[height - 3][width - 2] is Cell.Wall
            && cells[height - 2][width - 3] is Cell.Wall
        ) {
            throw CannotReachGoal()
        }
    }
}

class CannotReachGoal : Exception()

package dev.iaiabot.maze.mazegenerator.model

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Cells
import dev.iaiabot.maze.entity.XY
import dev.iaiabot.maze.mazegenerator.decorator.Decorator

class CellsImpl(
    override val width: Int,
    override val height: Int,
    private val decorator: Decorator,
) : Cells {
    private val cells: MutableMap<XY, Cell?> = mutableMapOf()
    private var wallCount: Int = 0
    override var start: Cell.Start? = null
        private set
    override var goal: Cell.Goal? = null
        private set

    override fun add(cell: Cell) {
        when (cells[cell.xy]) {
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
        cells[cell.xy] = cell
        decorator.sequentialOutput(cell)
    }

    override fun here(x: Int, y: Int): Cell? =
        try {
            cells[XY.convert(x, y)]
        } catch (e: ArrayIndexOutOfBoundsException) {
            null
        }

    override fun here(xy: XY): Cell? =
        try {
            cells[xy]
        } catch (e: ArrayIndexOutOfBoundsException) {
            null
        }

    override fun output() {
        decorator.fullOutput(cells)
    }
}

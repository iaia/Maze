package dev.iaiabot.maze.mazegenerator.decorator

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.XY

class StandardOutputDecorator(
    private val sequentialOutput: Boolean,
) : Decorator {
    override fun sequentialOutput(cell: Cell) {
        if (sequentialOutput) {
            println(cell.toString())
        }
    }

    override fun fullOutput(cells: Map<XY, Cell?>) {
        val width = cells.keys.maxOf { it.x } + 1
        val height = cells.keys.maxOf { it.y } + 1
        val displayCells: Array<Array<Cell?>> = Array(height) {
            Array(width) { null }
        }
        cells.keys.forEach {
            displayCells[it.y][it.x] = cells[it]
        }

        displayCells.forEach { column ->
            column.forEach { cell ->
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
}

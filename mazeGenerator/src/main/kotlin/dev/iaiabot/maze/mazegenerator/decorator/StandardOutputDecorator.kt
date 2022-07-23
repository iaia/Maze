package dev.iaiabot.maze.mazegenerator.decorator

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Status

class StandardOutputDecorator(
    private val sequentialOutput: Boolean,
) : Decorator {
    override fun onChangeStatus(status: Status) {
        println("$status--------------------------------")
    }

    override fun sequentialOutput(cell: Cell) {
        if (sequentialOutput) {
            println(cell.toString())
        }
    }

    override fun fullOutput(cells: Array<Array<Cell?>>) {
        cells.forEach { column ->
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

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
        val xys = cells.keys.sortedBy { it.x }.sortedBy { it.y }

        println(xys)

        xys.forEach { xy ->
            if (xy.x == 0) {
                println()
            }
            print(
                when (cells[xy]) {
                    is Cell.Start -> "s"
                    is Cell.Floor -> "_"
                    is Cell.Wall -> "x"
                    is Cell.Goal -> "g"
                    else -> "e"
                }
            )
        }
    }
}

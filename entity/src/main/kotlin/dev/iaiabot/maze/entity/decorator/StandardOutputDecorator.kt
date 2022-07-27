package dev.iaiabot.maze.entity.decorator

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Status

class StandardOutputDecorator(
    private val sequentialOutput: Boolean,
) : Decorator {

    private var status: Status = Status.INIT

    override fun onChangeBuildStatus(status: Status, cells: Collection<Collection<Cell>>) {
        this.status = status
        println("$status--------------------------------")
    }

    override fun outputSequentialBuilding(cell: Cell) {
        if (sequentialOutput) {
            if (status != Status.SETUP) {
                println("$cell")
            }
        }
    }

    override fun fullOutput(cells: Collection<Collection<Cell>>) {
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

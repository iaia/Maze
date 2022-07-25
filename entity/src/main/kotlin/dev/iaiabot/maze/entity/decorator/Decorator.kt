package dev.iaiabot.maze.entity.decorator

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Status

interface Decorator {
    fun onChangeStatus(status: Status, cells: Array<Array<Cell?>>) {}

    fun sequentialOutput(cell: Cell) {}
    fun fullOutput(cells: Array<Array<Cell?>>) {}
    fun batchOutput(
        cells: Array<Array<Cell?>>
    ) {
    }
}

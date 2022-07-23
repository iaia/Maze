package dev.iaiabot.maze.mazegenerator.decorator

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Status

interface Decorator {
    fun sequentialOutput(cell: Cell) {}
    fun fullOutput(cells: Array<Array<Cell?>>) {}
    fun onChangeStatus(status: Status) {}
}

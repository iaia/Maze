package dev.iaiabot.maze.entity.decorator

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Status

interface Decorator {
    fun sequentialOutput(cell: Cell, status: Status) {}
    fun fullOutput(cells: Array<Array<Cell?>>) {}
    fun onChangeStatus(status: Status) {}
}
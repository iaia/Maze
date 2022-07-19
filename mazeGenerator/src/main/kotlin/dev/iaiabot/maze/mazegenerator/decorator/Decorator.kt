package dev.iaiabot.maze.mazegenerator.decorator

import dev.iaiabot.maze.entity.Cell

interface Decorator {
    fun sequentialOutput(cell: Cell)
    fun fullOutput(cells: Array<Array<Cell?>>)
}

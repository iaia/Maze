package dev.iaiabot.maze.mazegenerator.decorator

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.XY

interface Decorator {
    fun sequentialOutput(cell: Cell)
    fun fullOutput(cells: Map<XY, Cell?>) {}
}

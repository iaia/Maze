package dev.iaiabot.maze.mazegenerator

import dev.iaiabot.maze.entity.Cells

interface Generator {
    fun setup(cells: Cells)
    fun buildMap()
}

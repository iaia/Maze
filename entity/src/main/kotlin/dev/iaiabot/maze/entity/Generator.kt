package dev.iaiabot.maze.entity

interface Generator {
    fun setup(cells: Cells)
    fun buildMap()
}

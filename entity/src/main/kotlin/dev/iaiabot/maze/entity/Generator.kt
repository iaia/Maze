package dev.iaiabot.maze.entity

interface Generator {
    val name: String
    fun setup(cells: Cells)
    fun buildMap()
}

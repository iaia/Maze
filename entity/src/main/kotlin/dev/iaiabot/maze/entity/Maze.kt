package dev.iaiabot.maze.entity

import dev.iaiabot.maze.entity.decorator.Decorator

class Maze(
    private val width: Int,
    private val height: Int,
    private val decorator: Decorator,
) {

    val start: Cell
        get() = cells.start!!
    private lateinit var cells: Cells
    private lateinit var generator: Generator

    fun setup(generator: Generator) {
        decorator.onChangeStatus(Status.SETUP)

        cells = Cells(width, height, decorator)
        this.generator = generator

        generator.setup(cells)
        decorator.onChangeStatus(Status.FINISH_SETUP)
    }

    fun buildMap() {
        decorator.onChangeStatus(Status.BUILDING)
        generator.buildMap()
        decorator.onChangeStatus(Status.FINISH_BUILD)
    }

    fun here(xy: XY): Cell? {
        return cells.here(xy)
    }

    fun output() {
        cells.output()
    }
}

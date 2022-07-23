package dev.iaiabot.maze.entity

import dev.iaiabot.maze.entity.decorator.Decorator

class Maze(
    width: Int,
    height: Int,
    private val generator: Generator,
    private val decorator: Decorator,
) {

    val start: Cell
        get() = cells.start!!
    private val cells = CellsImpl(width, height, decorator)

    fun setup() {
        decorator.onChangeStatus(Status.SETUP)
        generator.setup(cells)
        decorator.onChangeStatus(Status.FINISH_SETUP)
    }

    fun buildMap() {
        decorator.onChangeStatus(Status.BUILDING)
        generator.buildMap()
        decorator.onChangeStatus(Status.FINISHED)
    }

    fun here(xy: XY): Cell? {
        return cells.here(xy)
    }

    fun output() {
        cells.output()
    }
}

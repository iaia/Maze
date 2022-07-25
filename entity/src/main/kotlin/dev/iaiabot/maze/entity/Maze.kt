package dev.iaiabot.maze.entity

import dev.iaiabot.maze.entity.decorator.Decorator

class Maze(
    private val decorator: Decorator,
) {

    val start: Cell
        get() = cells.start!!
    private lateinit var cells: Cells
    private lateinit var generator: Generator

    fun setup(
        width: Int,
        height: Int,
        generator: Generator
    ) {
        decorator.onChangeStatus(Status.SETUP, emptyArray())

        cells = Cells(width, height, decorator)
        this.generator = generator

        generator.setup(cells)
        decorator.onChangeStatus(Status.FINISH_SETUP, cells.dump())
    }

    fun buildMap() {
        decorator.onChangeStatus(Status.BUILDING, cells.dump())
        generator.buildMap()
        cells.checkGoalAround()
        decorator.onChangeStatus(Status.FINISH_BUILD, cells.dump())
    }

    fun here(xy: XY): Cell? {
        return cells.here(xy)
    }

    fun output() {
        cells.output()
    }
}

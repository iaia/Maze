package dev.iaiabot.maze.mazegenerator.model

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Maze
import dev.iaiabot.maze.entity.Status
import dev.iaiabot.maze.entity.XY
import dev.iaiabot.maze.mazegenerator.Generator
import dev.iaiabot.maze.mazegenerator.decorator.Decorator

class MazeImpl(
    width: Int,
    height: Int,
    private val generator: Generator,
    private val decorator: Decorator,
) : Maze {

    private val cells = CellsImpl(width, height, decorator)
    override val start: Cell
        get() = cells.start!!

    override fun setup() {
        decorator.onChangeStatus(Status.SETUP)
        generator.setup(cells)
        decorator.onChangeStatus(Status.FINISH_SETUP)
    }

    override fun buildMap() {
        decorator.onChangeStatus(Status.BUILDING)
        generator.buildMap()
        decorator.onChangeStatus(Status.FINISHED)
    }

    override fun here(xy: XY): Cell? {
        return cells.here(xy)
    }

    override fun output() {
        cells.output()
    }
}

package dev.iaiabot.maze.mazegenerator.model

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Maze
import dev.iaiabot.maze.entity.XY
import dev.iaiabot.maze.mazegenerator.Generator
import dev.iaiabot.maze.mazegenerator.decorator.Decorator

class MazeImpl private constructor(
    width: Int,
    height: Int,
    private val generator: Generator,
    decorator: Decorator,
) : Maze {

    companion object {
        fun generate(
            width: Int,
            height: Int,
            generator: Generator,
            decorator: Decorator,
        ): Maze {
            return MazeImpl(width, height, generator, decorator)
        }
    }

    private val cells = CellsImpl(width, height, decorator)
    override val start: Cell
        get() = cells.start!!

    override fun setup() {
        generator.setup(cells)
    }

    override fun buildMap() {
        generator.buildMap()
    }

    override fun here(xy: XY): Cell? {
        return cells.here(xy)
    }

    override fun output() {
        cells.output()
    }
}

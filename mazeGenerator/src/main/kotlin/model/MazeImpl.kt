package model

import Cell
import Generator
import Maze
import XY
import decorator.Decorator

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

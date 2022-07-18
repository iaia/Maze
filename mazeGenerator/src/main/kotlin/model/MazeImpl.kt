package model

import Cell
import Generator
import Maze
import XY

class MazeImpl private constructor(
    width: Int,
    height: Int,
    private val generator: Generator,
    sequentialOutput: Boolean,
) : Maze {

    companion object {
        fun generate(
            width: Int,
            height: Int,
            generator: Generator,
            sequentialOutput: Boolean = false,
        ): Maze {
            return MazeImpl(width, height, generator, sequentialOutput)
        }
    }

    private val cells = CellsImpl(width, height, sequentialOutput)
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

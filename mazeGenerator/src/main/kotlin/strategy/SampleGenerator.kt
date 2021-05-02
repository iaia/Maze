package strategy

import Cell
import Generator
import Maze
import XY
import model.CellsImpl
import model.MazeImpl

class SampleGenerator(
    width: Int,
    height: Int,
) : Generator, BaseGenerator(width, height) {
    /**
     * xxxxx
     * x   x
     * xxx x
     * x   x
     * xxxxx
     */

    override fun generate(): Maze {
        cells = CellsImpl(width, height)

        buildMap()

        return MazeImpl(cells)
    }

    override fun buildMap() {
        cells.add(Cell.Wall(XY(1, 2)))
        cells.add(Cell.Wall(XY(2, 2)))

        cells.add(Cell.Floor(XY(2, 1)))
        cells.add(Cell.Floor(XY(3, 1)))
        cells.add(Cell.Floor(XY(3, 2)))
        cells.add(Cell.Floor(XY(2, 3)))
        cells.add(Cell.Floor(XY(3, 3)))

        cells.add(Cell.Start(XY(1, 1)))
        cells.add(Cell.Goal(XY(1, 3)))
    }
}

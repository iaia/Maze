package strategy

import Cell
import Cells
import Generator
import Maze
import MazeImpl
import XY

class SampleGenerator(
    private val width: Int,
    private val height: Int,
) : Generator {
    /**
     * xxxxx
     * x   x
     * xxx x
     * x   x
     * xxxxx
     */

    override fun generate(): Maze {
        val cells = Cells(width, height)

        cells.add(Cell.Wall(XY(1, 2)))
        cells.add(Cell.Wall(XY(2, 2)))

        cells.add(Cell.Floor(XY(2, 1)))
        cells.add(Cell.Floor(XY(3, 1)))
        cells.add(Cell.Floor(XY(3, 2)))
        cells.add(Cell.Floor(XY(2, 3)))
        cells.add(Cell.Floor(XY(3, 3)))

        cells.add(Cell.Start(XY(1, 1)))
        cells.add(Cell.Goal(XY(1, 3)))

        return MazeImpl(cells)
    }
}

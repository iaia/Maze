package strategy

import Cell
import Generator
import Maze
import MazeImpl
import XY

object SampleGenerator : Generator {
    /**
     * xxxxx
     * x   x
     * xxx x
     * x   x
     * xxxxx
     */

    override fun generate(): Maze {
        val cells = Array(5) {
            arrayOf<Cell>(
                Cell.Wall(XY(0, 0)),
                Cell.Wall(XY(1, 0)),
                Cell.Wall(XY(2, 0)),
                Cell.Wall(XY(3, 0)),
                Cell.Wall(XY(4, 0)),
            )
        }

        cells[1][0] = Cell.Wall(XY(0, 1))
        cells[1][4] = Cell.Wall(XY(4, 1))
        cells[2][0] = Cell.Wall(XY(0, 2))
        cells[2][4] = Cell.Wall(XY(4, 2))
        cells[3][0] = Cell.Wall(XY(0, 3))
        cells[3][4] = Cell.Wall(XY(4, 3))

        cells[4][0] = Cell.Wall(XY(0, 4))
        cells[4][1] = Cell.Wall(XY(1, 4))
        cells[4][2] = Cell.Wall(XY(2, 4))
        cells[4][3] = Cell.Wall(XY(3, 4))
        cells[4][4] = Cell.Wall(XY(4, 4))

        cells[2][1] = Cell.Wall(XY(1, 2))
        cells[2][2] = Cell.Wall(XY(2, 2))

        cells[1][2] = Cell.Floor(XY(2, 1))
        cells[1][3] = Cell.Floor(XY(3, 1))
        cells[2][3] = Cell.Floor(XY(3, 2))
        cells[3][2] = Cell.Floor(XY(2, 3))
        cells[3][3] = Cell.Floor(XY(3, 3))

        val start = Cell.Start(XY(1, 1))
        val goal = Cell.Goal(XY(1, 3))
        cells[1][1] = start
        cells[3][1] = goal

        return MazeImpl(cells, start.xy, goal.xy)
    }
}

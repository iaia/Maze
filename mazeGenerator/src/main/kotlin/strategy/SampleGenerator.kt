package strategy

import Cell
import Generator
import Maze
import MazeImpl

class SampleGenerator : Generator {
    /**
     * xxxxx
     * x   x
     * xxx x
     * x   x
     * xxxxx
     */

    override fun generate(): Maze {
        val cells = Array(5) { arrayOf<Cell>(Cell.Floor, Cell.Floor, Cell.Floor, Cell.Floor, Cell.Floor) }
        cells[0][0] = Cell.Wall
        cells[0][1] = Cell.Wall
        cells[0][2] = Cell.Wall
        cells[0][3] = Cell.Wall
        cells[0][4] = Cell.Wall

        cells[1][0] = Cell.Wall
        cells[1][4] = Cell.Wall
        cells[2][0] = Cell.Wall
        cells[2][4] = Cell.Wall
        cells[3][0] = Cell.Wall
        cells[3][4] = Cell.Wall

        cells[4][0] = Cell.Wall
        cells[4][1] = Cell.Wall
        cells[4][2] = Cell.Wall
        cells[4][3] = Cell.Wall
        cells[4][4] = Cell.Wall

        cells[2][1] = Cell.Wall
        cells[2][2] = Cell.Wall

        cells[1][1] = Cell.Start
        cells[3][1] = Cell.Goal

        return MazeImpl(cells, Pair(1, 1), Pair(3, 1))
    }
}

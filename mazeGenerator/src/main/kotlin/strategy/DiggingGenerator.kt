package strategy

import Cell
import Generator
import Maze
import XY
import model.CellsImpl
import model.MazeImpl

class DiggingGenerator(
    width: Int,
    height: Int,
) : Generator, BaseGenerator(width, height) {

    override fun generate(): Maze {
        cells = CellsImpl(width, height)

        buildMap()

        return MazeImpl(cells)
    }

    override fun buildMap() {
        setStartAndGoal()
        (1 until height - 1).forEach { y ->
            (1 until width - 1).forEach { x ->
                cells.add(Cell.Wall(XY(x, y)))
            }
        }
    }
}

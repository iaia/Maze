package strategy

import Cell
import Cells
import Generator
import Maze
import MazeImpl
import XY

class LayPillarGenerator : Generator {
    private var width: Int = 0
    private var height: Int = 0
    private lateinit var cells: Cells

    override fun generate(width: Int, height: Int): Maze {
        this.width = width
        this.height = height
        init()

        // TODO: あとで
        val start = Cell.Start(XY(0, 0))
        val goal = Cell.Goal(XY(0, 0))
        return MazeImpl(cells, start.xy, goal.xy)
    }

    private fun init() {
        cells = Array(width) {
            (0 until width).map { x ->
                Cell.Wall(XY(x, 0))
            }.toTypedArray()
        }
        (1 until height).forEach { y ->
            (0 until width).forEach { x ->
                cells[y][x] = when {
                    (x == 0 || y == 0) ||
                            (x == width - 1 || y == height - 1) -> {
                        Cell.Wall(XY(x, y))
                    }
                    x % 2 == 0 && y % 2 == 0 -> {
                        Cell.Wall(XY(x, y))
                    }
                    else -> {
                        Cell.Floor(XY(x, y))
                    }
                }
            }
        }
    }

    private fun layPillar() {
        cells.forEach { row ->
            row.forEach { cell ->
            }
        }
    }
}

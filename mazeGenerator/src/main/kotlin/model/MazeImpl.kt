package model

import Cell
import Cells
import Maze
import XY

internal class MazeImpl(
    private val cells: Cells,
) : Maze {

    override val start: Cell.Start
        get() = cells.start!!

    override val goal: Cell.Goal
        get() = cells.goal!!

    override fun here(xy: XY): Cell? {
        return cells.here(xy)
    }

    override fun output() {
        cells.output()
    }
}

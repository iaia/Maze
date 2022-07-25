package dev.iaiabot.maze.mazegenerator.strategy

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.XY

class SampleGenerator : BaseGenerator() {

    override val name: String = "Sample"

    /**
     * xxxxx
     * xs  x
     * xxx x
     * xg  x
     * xxxxx
     */

    override fun buildMap() {
        cells.add(Cell.Wall(XY(1, 2)))
        cells.add(Cell.Wall(XY(2, 2)))

        cells.add(Cell.Floor(XY(2, 1)))
        cells.add(Cell.Floor(XY(3, 1)))
        cells.add(Cell.Floor(XY(3, 2)))
        cells.add(Cell.Floor(XY(2, 3)))
        cells.add(Cell.Floor(XY(3, 3)))
    }
}

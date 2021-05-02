package strategy

import Cell
import XY

class DiggingGenerator(
    width: Int,
    height: Int,
) : BaseGenerator(width, height) {

    override fun buildMap() {
        setStartAndGoal()
        fillMap()
    }

    private fun fillMap() {
        (1 until height - 1).forEach { y ->
            (1 until width - 1).forEach { x ->
                cells.add(Cell.Wall(XY(x, y)))
            }
        }
    }
}

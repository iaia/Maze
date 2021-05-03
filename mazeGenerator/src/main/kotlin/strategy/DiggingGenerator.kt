package strategy

import Cell
import Direction
import XY

class DiggingGenerator(
    width: Int,
    height: Int,
) : BaseGenerator(width, height) {

    private val directions = arrayOf(
        Direction.ABOVE,
        Direction.LEFT,
        Direction.RIGHT,
        Direction.BELOW,
    )

    private val branches: MutableList<XY> = mutableListOf()

    override fun buildMap() {
        setStartAndGoal()
        fillMap()
        cells.add(Cell.Floor(XY(1, 1)))
        branches.add(XY(1, 1))
        var counter = 0
        while (branches.isNotEmpty() && counter < 1000) {
            val xy = branches.last()
            dig(xy)
            counter += 1
        }
    }

    private fun fillMap() {
        (1 until height - 1).forEach { y ->
            (1 until width - 1).forEach { x ->
                cells.add(Cell.Wall(XY(x, y)))
            }
        }
    }

    private fun dig(xy: XY) {
        val direction = randomDirection()
        val cell1 = cells.here(direction.calculate(xy))
        val cell2 = cells.here(direction.calculate(cell1.xy))
        if (canDig(cell1) && canDig(cell2)) {
            cells.add(Cell.Floor(cell1.xy))
            cells.add(Cell.Floor(cell2.xy))
            branches.add(cell2.xy)
        } else {
            branches.remove(xy)
        }
    }

    private fun canDig(cell: Cell): Boolean {
        return if (cell.xy.x > 0 || cell.xy.y > 0 || cell.xy.x < width || cell.xy.y < height) {
            true
        } else {
            cell !is Cell.Floor
        }
    }

    private fun randomDirection(): Direction {
        return directions.random()
    }
}

package strategy

import Cell
import Direction
import XY
import kotlin.random.Random

class WallExtendGenerator(
    width: Int,
    height: Int,
) : BaseGenerator(width, height) {
    private val selectedXY: MutableList<XY> = mutableListOf()

    private val extendDirection: Array<Direction> =
        arrayOf(Direction.LEFT, Direction.BELOW, Direction.ABOVE, Direction.RIGHT)

    override fun buildMap() {
        setStartAndGoal()
        fillMap()
        selectOuterWall()
        selectedXY.forEach {
            extendWall(it)
        }
    }

    private fun selectOuterWall() {
        for (x in 2 until width - 1 step 2) {
            if (Random.nextBoolean()) {
                selectedXY.add(XY(x, 0))
            }
            if (Random.nextBoolean()) {
                selectedXY.add(XY(x, height - 1))
            }
        }
        for (y in 2 until height - 1 step 2) {
            if (Random.nextBoolean()) {
                selectedXY.add(XY(0, y))
            }
            if (Random.nextBoolean()) {
                selectedXY.add(XY(width - 1, y))
            }
        }
    }

    private fun extendWall(xy: XY) {
        val direction = randomDirection()
        val cell1 = cells.here(direction.calculate(xy)) ?: return
        val cell2 = cells.here(direction.calculate(cell1.xy)) ?: return
        val cell3 = cells.here(direction.calculate(cell2.xy))
        if (cell1 is Cell.Floor && cell2 is Cell.Floor && cell3 is Cell.Floor) {
            cells.add(Cell.Wall(cell1.xy))
            cells.add(Cell.Wall(cell2.xy))
            extendWall(cell2.xy)
        }
    }

    private fun fillMap() {
        (1 until height - 1).forEach { y ->
            (1 until width - 1).forEach { x ->
                cells.add(Cell.Floor(XY(x, y)))
            }
        }
    }

    private fun randomDirection(): Direction {
        return extendDirection.random()
    }
}

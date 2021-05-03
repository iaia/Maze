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

        while (selectedXY.isNotEmpty()) {
            val xy = selectedXY.random()
            extendWall(xy)
            selectedXY.remove(xy)
        }
    }

    private fun selectOuterWall() {
        for (x in 2 until width - 1 step 2) {
            selectedXY.add(XY(x, 0))
            selectedXY.add(XY(x, height - 1))
        }
        for (y in 2 until height - 1 step 2) {
            selectedXY.add(XY(0, y))
            selectedXY.add(XY(width - 1, y))
        }
    }

    private fun extendWall(xy: XY, except: Array<Direction> = emptyArray()) {
        val direction = randomDirection(except) ?: return
        val cell1 = cells.here(direction.calculate(xy))
        if (cell1 == null) {
            extendWall(xy, except + direction)
            return
        }
        val cell2 = cells.here(direction.calculate(cell1.xy))
        if (cell2 == null) {
            extendWall(xy, except + direction)
            return
        }
        val cell3 = cells.here(direction.calculate(cell2.xy))
        if (cell3 == null) {
            extendWall(xy, except + direction)
            return
        }
        if (cell1 is Cell.Floor && cell2 is Cell.Floor && cell3 is Cell.Floor) {
            cells.add(Cell.Wall(cell1.xy))
            cells.add(Cell.Wall(cell2.xy))
            // 伸ばした先から更に伸ばすか、伸ばした元から別の方向に伸ばすか
            if (Random.nextBoolean()) {
                extendWall(xy, except + direction)
            }
            extendWall(cell2.xy)
        } else {
            extendWall(xy, except + direction)
        }
    }

    private fun fillMap() {
        (1 until height - 1).forEach { y ->
            (1 until width - 1).forEach { x ->
                cells.add(Cell.Floor(XY(x, y)))
            }
        }
    }

    private fun randomDirection(except: Array<Direction>): Direction? {
        return extendDirection.filterNot {
            except.contains(it)
        }.randomOrNull()
    }
}

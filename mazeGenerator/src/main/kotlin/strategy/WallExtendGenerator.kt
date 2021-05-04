package strategy

import Cell
import Direction
import XY

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
        selectPositions()

        while (selectedXY.isNotEmpty()) {
            val xy = selectedXY.random()
            if (cells.here(xy) is Cell.Floor) {
                extendWall(xy)
            }
            selectedXY.remove(xy)
        }
    }

    private fun selectPositions() {
        for (x in 2 until width - 1 step 2) {
            for (y in 2 until height - 1 step 2) {
                val xy = XY(x, y)
                if (cells.start?.xy == xy || cells.goal?.xy == xy) {
                    continue
                }

                selectedXY.add(xy)
            }
        }
    }

    private fun extendWall(
        xy: XY,
        building: MutableList<XY> = mutableListOf(),
        except: Array<Direction> = emptyArray()
    ) {
        val cell0 = cells.here(xy)
        if (cell0 !is Cell.Floor) {
            return
        }

        val direction = randomDirection(except) ?: return
        val cell1 = cells.here(direction.calculate(xy))
        if (cell1 == null || building.contains(cell1.xy)) {
            extendWall(xy, building, except + direction)
            return
        }
        val cell2 = cells.here(direction.calculate(cell1.xy))
        if (cell2 == null || building.contains(cell2.xy)) {
            extendWall(xy, building, except + direction)
            return
        }

        if (cell1 is Cell.Start || cell1 is Cell.Goal || cell2 is Cell.Start || cell2 is Cell.Goal) {
            extendWall(xy, building, except + direction)
            return
        }

        if (cell1 is Cell.Floor) {
            cells.add(Cell.Wall(cell0.xy))
            building.add(cell0.xy)
            cells.add(Cell.Wall(cell1.xy))
            building.add(cell1.xy)
        }
        extendWall(cell2.xy)
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

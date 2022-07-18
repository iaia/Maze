package strategy

import Cell
import Direction
import XY

class DiggingGenerator : BaseGenerator() {

    private val directions = arrayOf(
        Direction.ABOVE,
        Direction.LEFT,
        Direction.RIGHT,
        Direction.BELOW,
    )

    private val branches: MutableMap<XY, Array<Direction>> = mutableMapOf()

    override fun buildMap() {
        fillMap()
        cells.add(Cell.Floor(XY(1, 1)))
        branches[XY(1, 1)] = emptyArray()
        var counter = 0
        while (branches.isNotEmpty() && counter < 1000) {
            val xy = branches.entries.last()
            dig(xy.key, xy.value)
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

    private fun dig(xy: XY, except: Array<Direction>) {
        val direction = randomDirection(except)
        if (direction == null) {
            branches.remove(xy)
            return
        }
        branches[xy] = except + direction
        val cell1 = cells.here(direction.calculate(xy)) ?: throw Exception("")
        val cell2 = cells.here(direction.calculate(cell1.xy))
        if (canDig(cell1) && canDig(cell2)) {
            cells.add(Cell.Floor(cell1.xy))
            cells.add(Cell.Floor(cell2!!.xy))
            branches[cell2.xy] = arrayOf(direction)
        }
    }

    private fun canDig(cell: Cell?): Boolean {
        cell ?: return false
        return if (cell.xy.x > 0 || cell.xy.y > 0 || cell.xy.x < width || cell.xy.y < height) {
            cell !is Cell.Floor
        } else {
            false
        }
    }

    private fun randomDirection(except: Array<Direction> = emptyArray()): Direction? {
        return directions.filterNot {
            except.contains(it)
        }.randomOrNull()
    }
}

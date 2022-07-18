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

    private val branches: MutableMap<XY, MutableList<Direction>> = mutableMapOf()

    private var alreadyDugToGoal: Boolean = false

    override fun buildMap() {
        fillMap()
        branches[XY(1, 1)] = mutableListOf(Direction.LEFT, Direction.ABOVE)
        while (branches.isNotEmpty()) {
            val xy = branches.entries.last()
            dig(xy.key, xy.value)
        }
    }

    private fun fillMap() {
        (1 until height - 1).forEach { y ->
            (1 until width - 1).forEach { x ->
                cells.add(Cell.Wall(XY(x, y)))
            }
        }
    }

    private fun dig(xy: XY, except: List<Direction>) {
        val direction = randomDirection(except)
        if (direction == null) {
            branches.remove(xy)
            return
        }
        branches[xy]?.add(direction)
        val cell1 = cells.here(direction.calculate(xy)) ?: throw Exception("")
        val cell2 = cells.here(direction.calculate(cell1.xy))
        if (canDig(cell1) && canDig(cell2)) {
            cells.add(Cell.Floor(cell1.xy))
            when (cell2) {
                is Cell.Start -> {
                }
                is Cell.Goal -> {
                    alreadyDugToGoal = true
                }
                is Cell.Floor -> {
                    branches[cell2.xy]?.add(direction.opposite())
                }
                is Cell.Wall -> {
                    cells.add(Cell.Floor(cell2.xy))
                    branches[cell2.xy] = mutableListOf(direction.opposite())
                }
                else -> {}
            }
        }
    }

    private fun canDig(cell: Cell?): Boolean {
        cell ?: return false
        if (cell is Cell.Start) {
            return false
        }
        if (cell is Cell.Goal && alreadyDugToGoal) {
            return false
        }
        return if (cell.xy.x > 0 || cell.xy.y > 0 || cell.xy.x < width || cell.xy.y < height) {
            cell !is Cell.Floor
        } else {
            false
        }
    }

    private fun randomDirection(except: List<Direction> = emptyList()): Direction? {
        return directions.filterNot {
            except.contains(it)
        }.randomOrNull()
    }
}

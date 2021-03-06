package dev.iaiabot.maze.mazegenerator.strategy

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Cells
import dev.iaiabot.maze.entity.Direction
import dev.iaiabot.maze.entity.XY

class LayPillarGenerator : BaseGenerator() {

    override val name: String = "LayPillar"

    private val layDirections = arrayOf(Direction.LEFT, Direction.RIGHT, Direction.BELOW)
    private val layDirectionsForFirst = arrayOf(Direction.LEFT, Direction.RIGHT, Direction.BELOW, Direction.ABOVE)

    override fun setup(cells: Cells) {
        super.setup(cells)
        buildPillar()
    }

    override fun buildMap() {
        layPillar()
    }

    private fun buildPillar() {
        (1 until height - 1).forEach { y ->
            (1 until width - 1).forEach { x ->
                cells.add(
                    when {
                        x % 2 == 0 && y % 2 == 0 -> {
                            Cell.Wall(XY(x, y))
                        }
                        else -> {
                            Cell.Floor(XY(x, y))
                        }
                    }
                )
            }
        }
    }

    private fun layPillar() {
        // 0は壁しかない 奇数は壁がないので無視
        for (y in 2 until (height - 1) step 2) {
            for (x in 2 until (width - 1) step 2) {
                lay(cells.here(x, y))
            }
        }
    }

    private fun lay(cell: Cell?, exceptDirections: Array<Direction> = emptyArray()) {
        cell ?: return
        val direction = if (cell.xy.y == 2) {
            randomDirectionForFirst(exceptDirections)
        } else {
            randomDirection(exceptDirections)
        }

        val xy = direction.calculate(cell.xy)
        if (cells.here(xy) is Cell.Floor) {
            cells.add(Cell.Wall(xy))
        } else {
            lay(cell, exceptDirections + direction)
        }
    }

    private fun randomDirectionForFirst(exceptDirections: Array<Direction> = emptyArray()): Direction {
        return layDirectionsForFirst.filterNot {
            exceptDirections.contains(it)
        }.random()
    }

    private fun randomDirection(exceptDirections: Array<Direction> = emptyArray()): Direction {
        return layDirections.filterNot {
            exceptDirections.contains(it)
        }.random()
    }
}

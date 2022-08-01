package dev.iaiabot.maze.mazeresolver.strategy

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Direction
import dev.iaiabot.maze.entity.Resolver
import dev.iaiabot.maze.entity.XY

// sample generator のみ解決出来るresolver
// それ以外は6手以上かかる場合に失敗する
class SampleResolver : Resolver {
    private val footprints = mutableListOf<XY>()

    override fun resolve(
        currentCell: () -> Cell,
        currentPosition: () -> XY,
        isGoal: () -> Boolean,
        checkCell: (Direction) -> Cell?,
        move: (Direction) -> Unit,
    ) {
        var counter = 0

        while (!isGoal() && counter <= 6) {
            counter += 1
            footprints.add(currentPosition())
            val direction = lookAround(checkCell)
            move(direction)
        }
    }

    private fun lookAround(checkCell: (Direction) -> Cell?): Direction {
        var cell = checkCell(Direction.LEFT)
        if (
            cell != null &&
            cell !is Cell.Wall &&
            !alreadyTraversed(cell.xy)
        ) {
            return Direction.LEFT
        }
        cell = checkCell(Direction.RIGHT)
        if (
            cell != null &&
            cell !is Cell.Wall &&
            !alreadyTraversed(cell.xy)
        ) {
            return Direction.RIGHT
        }
        cell = checkCell(Direction.ABOVE)
        if (
            cell != null &&
            cell !is Cell.Wall &&
            !alreadyTraversed(cell.xy)
        ) {
            return Direction.ABOVE
        }
        cell = checkCell(Direction.BELOW)
        if (
            cell != null &&
            cell !is Cell.Wall &&
            !alreadyTraversed(cell.xy)
        ) {
            return Direction.BELOW
        }
        throw Exception()
    }

    private fun alreadyTraversed(nextPosition: XY): Boolean {
        return footprints.contains(nextPosition)
    }
}

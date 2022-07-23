package dev.iaiabot.maze.mazeresolver.strategy

import dev.iaiabot.maze.entity.*

// sample generator のみ解決出来るresolver
// それ以外は6手以上かかる場合に失敗する
class SampleResolver : Resolver {
    private val footprints = mutableListOf<XY>()

    override fun resolve(player: Player) {
        var counter = 0

        while (!player.isGoal() && counter <= 6) {
            counter += 1
            footprints.add(player.currentPosition())
            val direction = lookAround(player)
            player.move(direction)
        }
    }

    private fun lookAround(player: Player): Direction {
        var cell = player.checkCell(Direction.LEFT)
        if (
            cell != null &&
            cell !is Cell.Wall &&
            !alreadyTraversed(cell.xy)
        ) {
            return Direction.LEFT
        }
        cell = player.checkCell(Direction.RIGHT)
        if (
            cell != null &&
            cell !is Cell.Wall &&
            !alreadyTraversed(cell.xy)
        ) {
            return Direction.RIGHT
        }
        cell = player.checkCell(Direction.ABOVE)
        if (
            cell != null &&
            cell !is Cell.Wall &&
            !alreadyTraversed(cell.xy)
        ) {
            return Direction.ABOVE
        }
        cell = player.checkCell(Direction.BELOW)
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

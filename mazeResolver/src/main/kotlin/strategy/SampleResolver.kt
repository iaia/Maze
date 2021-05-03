package strategy

import Cell
import Direction
import Player
import Resolver
import XY

// sample generator のみ解決出来るresolver
// それ以外は6手以上かかる場合に失敗する
class SampleResolver : Resolver {
    override var moveCounter = 0

    private val footprints = mutableListOf<XY>()

    override fun resolve(player: Player) {
        while (!player.isGoal() && moveCounter < 6) {
            footprints.add(player.currentPosition())
            val direction = lookAround(player)
            player.move(direction)
            moveCounter += 1
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
        return Direction.STOP
    }

    private fun alreadyTraversed(nextPosition: XY): Boolean {
        return footprints.contains(nextPosition)
    }
}

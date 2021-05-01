package strategy

import Cell
import Direction
import Player
import Resolver
import XY

class SampleResolver : Resolver {
    private val footprints = mutableListOf<XY>()

    override fun resolve(player: Player) {
        var counter = 0
        while (!player.isGoal() && counter < 6) {
            footprints.add(player.currentPosition())
            val direction = lookAround(player)
            player.move(direction)
            counter += 1
        }
    }

    private fun lookAround(player: Player): Direction {
        var cell = player.checkCell(Direction.LEFT)
        if (
            cell !is Cell.Wall &&
            !alreadyTraversed(cell.xy)
        ) {
            return Direction.LEFT
        }
        cell = player.checkCell(Direction.RIGHT)
        if (
            cell !is Cell.Wall &&
            !alreadyTraversed(cell.xy)
        ) {
            return Direction.RIGHT
        }
        cell = player.checkCell(Direction.ABOVE)
        if (
            cell !is Cell.Wall &&
            !alreadyTraversed(cell.xy)
        ) {
            return Direction.ABOVE
        }
        cell = player.checkCell(Direction.BELOW)
        if (
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

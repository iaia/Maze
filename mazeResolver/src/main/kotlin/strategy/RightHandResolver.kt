package strategy

import Cell
import Direction
import Player
import Resolver

class RightHandResolver : Resolver {
    override fun resolve(player: Player) {
        var counter = 0
        while (!player.isGoal() && counter < 6) {
            val direction = lookAround(player)
            player.move(direction)
            counter += 1
        }
    }

    private fun lookAround(player: Player): Direction {
        var cell = player.checkCell(Direction.LEFT)
        if (
            cell !is Cell.Wall
        ) {
            return Direction.LEFT
        }
        cell = player.checkCell(Direction.RIGHT)
        if (
            cell !is Cell.Wall
        ) {
            return Direction.RIGHT
        }
        cell = player.checkCell(Direction.ABOVE)
        if (
            cell !is Cell.Wall
        ) {
            return Direction.ABOVE
        }
        cell = player.checkCell(Direction.BELOW)
        if (
            cell !is Cell.Wall
        ) {
            return Direction.BELOW
        }
        return Direction.STOP
    }
}

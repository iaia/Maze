package strategy

import Direction
import Player
import Resolver

class SampleResolver : Resolver {
    override fun resolve(player: Player) {
        var counter = 0
        while (!player.isGoal() && counter < 10) {
            val direction = lookAround(player)
            player.move(direction)
            counter += 1
        }
    }

    private fun lookAround(player: Player): Direction {
        if (player.canGo(Direction.LEFT)) {
            return Direction.LEFT
        }
        if (player.canGo(Direction.RIGHT)) {
            return Direction.RIGHT
        }
        if (player.canGo(Direction.ABOVE)) {
            return Direction.ABOVE
        }
        if (player.canGo(Direction.BELOW)) {
            return Direction.BELOW
        }
        return Direction.STOP
    }
}

package strategy

import Player
import Resolver
import XY

class SampleResolver : Resolver {
    override fun resolve(player: Player) {
        var counter = 0
        while (!player.isGoal() && counter < 10) {
            player.move(XY(0, 1))
            counter += 1
        }
    }

    private fun lookAround(player: Player): XY {
        val xy = player.currentPosition()

        when {
            // left
            player.canGo(xy.x - 1, xy.y) -> Unit
            // right
            player.canGo(xy.x + 1, xy.y) -> Unit
            // above
            player.canGo(xy.x, xy.y - 1) -> Unit
            // below
            player.canGo(xy.x, xy.y + 1) -> Unit
        }
    }
}

package strategy

import Player
import Resolver
import XY

class SampleResolver : Resolver {
    override fun resolve(player: Player) {
        //while (!player.isGoal()) {
        player.move(XY(0, 1))
        //}
    }
}

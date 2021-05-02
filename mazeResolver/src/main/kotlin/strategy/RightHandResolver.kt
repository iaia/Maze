package strategy

import Cell
import Direction
import Player
import Resolver

class RightHandResolver : Resolver {
    // Playerは初期の向いている方角が無いので、とりあえず上にする
    private var playerDirection: Direction = Direction.ABOVE
    override var moveCounter = 0

    override fun resolve(player: Player) {
        while (!player.isGoal() && moveCounter < 1000) {
            // 右側を見る
            var direction = lookRight()
            // 右側に壁がなければ、右側を正面にし、そちらに進む
            if (player.checkCell(direction) !is Cell.Wall) {
                turnRight()
                player.move(direction)
                moveCounter += 1
                continue
            }
            // 前方を見る
            direction = lookFront()
            // 前方に壁がなければ、前進する
            if (player.checkCell(direction) !is Cell.Wall) {
                player.move(direction)
                moveCounter += 1
                continue
            }
            // 進めなかったとき、左を向く
            turnLeft()
        }
    }

    private fun lookRight(): Direction {
        return when (playerDirection) {
            Direction.ABOVE -> Direction.RIGHT
            Direction.RIGHT -> Direction.BELOW
            Direction.BELOW -> Direction.LEFT
            Direction.LEFT -> Direction.ABOVE
            else -> Direction.STOP
        }
    }

    private fun lookFront(): Direction {
        return when (playerDirection) {
            Direction.ABOVE -> Direction.ABOVE
            Direction.RIGHT -> Direction.RIGHT
            Direction.BELOW -> Direction.BELOW
            Direction.LEFT -> Direction.LEFT
            else -> Direction.STOP
        }
    }

    private fun turnRight() {
        playerDirection = when (playerDirection) {
            Direction.ABOVE -> Direction.RIGHT
            Direction.RIGHT -> Direction.BELOW
            Direction.BELOW -> Direction.LEFT
            Direction.LEFT -> Direction.ABOVE
            else -> Direction.STOP
        }
    }

    private fun turnLeft() {
        playerDirection = when (playerDirection) {
            Direction.ABOVE -> Direction.LEFT
            Direction.RIGHT -> Direction.ABOVE
            Direction.BELOW -> Direction.RIGHT
            Direction.LEFT -> Direction.BELOW
            else -> Direction.STOP
        }
    }
}

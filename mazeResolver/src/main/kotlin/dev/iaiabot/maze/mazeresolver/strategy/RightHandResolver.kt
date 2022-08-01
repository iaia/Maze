package dev.iaiabot.maze.mazeresolver.strategy

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Direction
import dev.iaiabot.maze.entity.Resolver
import dev.iaiabot.maze.entity.XY

// 右手法
class RightHandResolver : Resolver {
    // Playerは初期の向いている方角が無いので、とりあえず上にする
    private var playerDirection: Direction = Direction.ABOVE

    override fun resolve(
        currentCell: () -> Cell,
        currentPosition: () -> XY,
        isGoal: () -> Boolean,
        checkCell: (Direction) -> Cell?,
        move: (Direction) -> Unit,
    ) {
        var counter = 0
        while (!isGoal() && counter < 1000) {
            counter += 1

            // 右側を見る
            var direction = lookRight()
            // 右側に壁がなければ、右側を正面にし、そちらに進む
            if (checkCell(direction) !is Cell.Wall) {
                turnRight()
                move(direction)
                continue
            }
            // 前方を見る
            direction = lookFront()
            // 前方に壁がなければ、前進する
            if (checkCell(direction) !is Cell.Wall) {
                move(direction)
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
        }
    }

    private fun lookFront(): Direction {
        return when (playerDirection) {
            Direction.ABOVE -> Direction.ABOVE
            Direction.RIGHT -> Direction.RIGHT
            Direction.BELOW -> Direction.BELOW
            Direction.LEFT -> Direction.LEFT
        }
    }

    private fun turnRight() {
        playerDirection = when (playerDirection) {
            Direction.ABOVE -> Direction.RIGHT
            Direction.RIGHT -> Direction.BELOW
            Direction.BELOW -> Direction.LEFT
            Direction.LEFT -> Direction.ABOVE
        }
    }

    private fun turnLeft() {
        playerDirection = when (playerDirection) {
            Direction.ABOVE -> Direction.LEFT
            Direction.RIGHT -> Direction.ABOVE
            Direction.BELOW -> Direction.RIGHT
            Direction.LEFT -> Direction.BELOW
        }
    }
}

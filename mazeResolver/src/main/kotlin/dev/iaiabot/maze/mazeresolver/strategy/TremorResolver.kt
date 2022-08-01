package dev.iaiabot.maze.mazeresolver.strategy

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Direction
import dev.iaiabot.maze.entity.Resolver
import dev.iaiabot.maze.entity.XY

class TremorResolver : Resolver {
    private val alreadyPassBranches = mutableListOf<Cell?>()

    override fun resolve(
        currentCell: () -> Cell,
        currentPosition: () -> XY,
        isGoal: () -> Boolean,
        checkCell: (Direction) -> Cell?,
        move: (Direction) -> Unit,
    ) {
        var counter = 0
        var previousCell: Cell? = null

        while (!isGoal() && counter < 1000) {
            counter += 1

            // 今いる周囲を見渡す
            val allDirection: Map<Direction, Cell?> = Direction.values().associateWith { direction ->
                val cell = checkCell(direction)
                // もしゴールがすでにあれば探索を中断してそちらへ進む
                if (cell is Cell.Goal) {
                    previousCell = cell
                    move(direction)
                    return
                }
                cell
            }
            val floorDirection = allDirection.filter { it.value !is Cell.Wall }
            when (floorDirection.size) {
                0 -> throw Exception()
                1 -> {
                    // 1方向しか無い(つまり行き止まり)の場合、戻る
                    previousCell = currentCell()
                    move(floorDirection.keys.first())
                }
                2 -> {
                    // 2方向しか床が無い場合(つまり通路)、前回通った床以外を通る
                    floorDirection.entries.find { it.value != previousCell }?.also {
                        previousCell = currentCell()
                        move(it.key)
                    }
                }
                else -> {
                    // 3方向以上に床がある場合、前回通った床とすでに通った分岐以外のどれかをランダムで通る
                    // わざわざランダムで座標を決める必要はないので最初に見つかったやつでいい
                    floorDirection.entries.find {
                        it.value != previousCell &&
                                !alreadyPassBranches.contains(it.value) // TODO: 分岐になる床をキーになるMapにしておくと、全部のブランチを検索しなくて済む
                    }?.also { anyOne ->
                        val cell = anyOne.value ?: throw Exception()
                        // 前回通った床と、今回通ろうとする床を記憶する (分岐床)
                        alreadyPassBranches.add(previousCell)
                        previousCell = currentCell()
                        alreadyPassBranches.add(cell)
                        move(anyOne.key)
                    }
                }
            }
        }
    }
}

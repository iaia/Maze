package dev.iaiabot.maze.mazegenerator.strategy

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Cells
import dev.iaiabot.maze.entity.Generator
import dev.iaiabot.maze.entity.XY
import kotlin.random.Random

abstract class BaseGenerator : Generator {
    protected lateinit var cells: Cells

    protected val width: Int
        get() = cells.width

    protected val height: Int
        get() = cells.height

    override fun setup(cells: Cells) {
        this.cells = cells
        // 外壁を作る
        (0 until width).forEach { x ->
            cells.add(Cell.Wall(XY(x, 0)))
        }
        (1 until height).forEach { y ->
            cells.add(Cell.Wall(XY(width - 1, y)))
        }
        (width - 1 downTo 0).forEach { x ->
            cells.add(Cell.Wall(XY(x, height - 1)))
        }
        (height - 1 downTo 1).forEach { y ->
            cells.add(Cell.Wall(XY(0, y)))
        }

        setStartAndGoal()
    }

    private fun setStartAndGoal() {
        cells.add(Cell.Start(XY(1, 1)))
        if (width == 5 && height == 5) {
            cells.add(Cell.Goal(XY(1, 3)))
        } else {
            cells.add(Cell.Goal(generateRandomGoalXY()))
        }
    }

    private fun generateRandomGoalXY(): XY {
        val x = Random.nextInt(3, width - 1)
        val y = Random.nextInt(3, height - 1)
        return when {
            x % 2 == 0 && y % 2 == 0 -> {
                XY(x + 1, y + 1)
            }
            x % 2 == 0 -> {
                XY(x + 1, y)
            }
            y % 2 == 0 -> {
                XY(x, y + 1)
            }
            else -> {
                XY(x, y)
            }
        }
    }
}

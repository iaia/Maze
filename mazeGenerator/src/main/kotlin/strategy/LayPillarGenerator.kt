package strategy

import Cell
import Cells
import Direction
import Generator
import Maze
import XY
import model.MazeImpl
import kotlin.random.Random

class LayPillarGenerator(
    private var width: Int,
    private var height: Int,
) : Generator {
    private lateinit var cells: Cells
    private val layDirections = arrayOf(Direction.LEFT, Direction.RIGHT, Direction.BELOW)
    private val layDirectionsForFirst = arrayOf(Direction.LEFT, Direction.RIGHT, Direction.BELOW, Direction.ABOVE)

    override fun generate(): Maze {
        cells = Cells(width, height)
        buildMap()
        layPillar()

        return MazeImpl(cells)
    }

    private fun setStartAndGoal() {
        val startXY = generateRandomXY()
        val goalXY = generateRandomXY(startXY)
        cells.add(Cell.Start(startXY))
        cells.add(Cell.Goal(goalXY))
    }

    private fun generateRandomXY(except: XY? = null): XY {
        val x = Random.nextInt(1, width - 1)
        val y = Random.nextInt(1, height - 1)
        return if (x == except?.x && y == except.y) {
            XY(
                if (x % 2 == 0) {
                    x + 1
                } else {
                    x
                },
                if (y % 2 == 0) {
                    y + 1
                } else {
                    y
                }
            )
        } else if (x % 2 == 0 && y % 2 == 0) {
            XY(x + 1, y + 1)
        } else {
            XY(x, y)
        }
    }

    private fun buildMap() {
        setStartAndGoal()
        (1 until height - 1).forEach { y ->
            (1 until width - 1).forEach { x ->
                cells.add(
                    when {
                        x % 2 == 0 && y % 2 == 0 -> {
                            Cell.Wall(XY(x, y))
                        }
                        else -> {
                            Cell.Floor(XY(x, y))
                        }
                    }
                )
            }
        }
    }

    private fun layPillar() {
        // 0は壁しかない 奇数は壁がないので無視
        for (y in 2 until (height - 1) step 2) {
            for (x in 2 until (width - 1) step 2) {
                lay(cells.here(x, y))
            }
        }
    }

    private fun lay(cell: Cell, exceptDirections: Array<Direction> = emptyArray()) {
        val direction = if (cell.xy.y == 2) {
            randomDirectionForFirst(exceptDirections)
        } else {
            randomDirection(exceptDirections)
        }

        val xy = direction.calculate(cell.xy)
        if (cells.here(xy) is Cell.Floor) {
            cells.add(Cell.Wall(xy))
        } else {
            lay(cell, exceptDirections + direction)
        }
    }

    private fun randomDirectionForFirst(exceptDirections: Array<Direction> = emptyArray()): Direction {
        return layDirectionsForFirst.filterNot {
            exceptDirections.contains(it)
        }[Random.nextInt(4 - exceptDirections.size)]
    }

    private fun randomDirection(exceptDirections: Array<Direction> = emptyArray()): Direction {
        return layDirections.filterNot {
            exceptDirections.contains(it)
        }[Random.nextInt(3 - exceptDirections.size)]
    }
}

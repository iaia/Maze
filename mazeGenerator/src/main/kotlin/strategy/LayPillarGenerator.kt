package strategy

import Cell
import Cells
import Direction
import Generator
import Maze
import MazeImpl
import XY
import kotlin.random.Random

class LayPillarGenerator : Generator {
    private var width: Int = 0
    private var height: Int = 0
    private lateinit var cells: Cells
    private val layDirections = arrayOf(Direction.LEFT, Direction.RIGHT, Direction.BELOW)
    private val layDirectionsForFirst = arrayOf(Direction.LEFT, Direction.RIGHT, Direction.BELOW, Direction.ABOVE)

    override fun generate(width: Int, height: Int): Maze {
        this.width = width
        this.height = height
        init()
        buildPillar()
        layPillar()

        setStart()
        setGoal()

        return MazeImpl(cells)
    }

    private fun init() {
        cells = Cells(width, height)
    }

    private fun buildPillar() {
        (1 until height).forEach { y ->
            (0 until width).forEach { x ->
                cells.add(
                    when {
                        (x == 0 || y == 0) ||
                                (x == width - 1 || y == height - 1) -> {
                            Cell.Wall(XY(x, y))
                        }
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
        println("cell: $cell, direction: $direction")

        val xy = direction.calculate(cell.xy)
        if (cells.here(xy) is Cell.Wall) {
            lay(cell, exceptDirections + direction)
        } else {
            cells.add(Cell.Wall(xy))
        }
    }

    private fun setStart(challengeNum: Int = 0) {
        val y = Random.nextInt(height)
        val x = Random.nextInt(width)

        if (challengeNum > 10) {
            throw Exception("cannot set start")
        }
        if (cells.here(x, y) is Cell.Floor) {
            Cell.Start(XY(x, y)).also {
                cells.add(it)
            }
        } else {
            setStart(challengeNum + 1)
        }
    }

    private fun setGoal(challengeNum: Int = 0) {
        val y = Random.nextInt(height)
        val x = Random.nextInt(width)

        if (challengeNum > 10) {
            throw Exception("cannot set start")
        }
        if (cells.here(x, y) is Cell.Floor) {
            Cell.Goal(XY(x, y)).also {
                cells.add(it)
            }
        } else {
            setGoal(challengeNum + 1)
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

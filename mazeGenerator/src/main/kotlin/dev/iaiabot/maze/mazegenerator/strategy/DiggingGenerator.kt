package dev.iaiabot.maze.mazegenerator.strategy

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Cells
import dev.iaiabot.maze.entity.Direction
import dev.iaiabot.maze.entity.XY

class DiggingGenerator(
    private val priority: Priority,
) : BaseGenerator() {

    private val branches = mutableListOf<Branch>()
    private var alreadyDugToGoal: Boolean = false

    override fun setup(cells: Cells) {
        super.setup(cells)
        fillMap()
    }

    override fun buildMap() {
        branches.add(Branch(XY(1, 1)))

        var branch: Branch? = null
        while (true) {
            branch = when (priority) {
                Priority.DEPTH_FIRST -> branches.last { !it.finished }
                Priority.BREADTH_FIRST -> {
                    if (branch == null || branch.finished) {
                        branches.last { !it.finished }
                    } else {
                        branch
                    }
                }
                Priority.RANDOM -> branches.filter { !it.finished }.random()
            }
            dig(branch)
            if (branches.all { it.finished }) {
                break
            }
        }
    }

    private fun fillMap() {
        (1 until height - 1).forEach { y ->
            (1 until width - 1).forEach { x ->
                cells.add(Cell.Wall(XY(x, y)))
            }
        }
    }

    private fun dig(branch: Branch) {
        val direction = branch.getDirection()
        val xy = branch.xy

        val cell1 = cells.here(direction.calculate(xy)) ?: throw Exception("")
        val cell2 = cells.here(direction.calculate(cell1.xy))
        if (canDig(cell1) && canDig(cell2)) {
            cells.add(Cell.Floor(cell1.xy))

            when (cell2) {
                is Cell.Goal -> {
                    alreadyDugToGoal = true
                }
                is Cell.Wall -> {
                    cells.add(Cell.Floor(cell2.xy))

                    val nextBranch = Branch(cell2.xy, direction.opposite())
                    branches.add(nextBranch)
                }
                else -> {}
            }
        }
    }

    private fun canDig(cell: Cell?): Boolean {
        cell ?: return false
        if (cell is Cell.Start) {
            return false
        }
        if (cell is Cell.Goal && alreadyDugToGoal) {
            return false
        }
        return if (cell.xy.x > 0 || cell.xy.y > 0 || cell.xy.x < width || cell.xy.y < height) {
            cell !is Cell.Floor
        } else {
            false
        }
    }

    class Branch(
        val xy: XY,
        cameFrom: Direction? = null,
    ) {
        var finished: Boolean = false
        private val directions: MutableList<Direction> = mutableListOf(
            Direction.LEFT, Direction.RIGHT, Direction.ABOVE, Direction.BELOW,
        )

        init {
            directions.remove(cameFrom)
        }

        fun getDirection(): Direction {
            val direction = directions.random()
            directions.remove(direction)
            if (directions.isEmpty()) {
                finished = true
            }
            return direction
        }

        fun output() {
            println("${xy}, ${directions.joinToString(",")}, $finished")
            println()
        }
    }

    enum class Priority {
        DEPTH_FIRST,
        BREADTH_FIRST,
        RANDOM,
    }
}

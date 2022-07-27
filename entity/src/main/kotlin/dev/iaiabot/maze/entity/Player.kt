package dev.iaiabot.maze.entity

import dev.iaiabot.maze.entity.decorator.Decorator

class Player(
    private val maze: Maze,
    private val resolver: Resolver,
    private val decorator: Decorator,
) {
    lateinit var currentCell: Cell
    private var moveCounter: Int = 0
    private val procedures = mutableListOf<Cell>()

    fun start() {
        decorator.onChangeResolveStatus(Status.START_RESOLVE, procedures)
        moveToStartPosition()
        decorator.onChangeResolveStatus(status = Status.RESOLVING, procedures)
        resolver.resolve(this)
        decorator.onChangeResolveStatus(Status.FINISH_RESOLVE, procedures)
    }

    fun move(direction: Direction) {
        val xy = direction.calculate(currentCell.xy.x, currentCell.xy.y)
        when (val cell = maze.here(xy)) {
            is Cell.Start, is Cell.Goal, is Cell.Floor -> move(cell)
            is Cell.Wall -> return
            else -> {}
        }
    }

    fun isGoal(): Boolean = maze.here(currentPosition()) is Cell.Goal

    fun currentPosition(): XY = currentCell.xy

    fun checkCell(direction: Direction): Cell? {
        return maze.here(direction.calculate(currentCell.xy))
    }

    private fun moveToStartPosition() {
        moveCounter = -1
        move(maze.start)
    }

    private fun move(cell: Cell) {
        currentCell = cell
        moveCounter += 1
        procedures.add(cell)
        decorator.outputSequentialResolving(procedures)
    }
}

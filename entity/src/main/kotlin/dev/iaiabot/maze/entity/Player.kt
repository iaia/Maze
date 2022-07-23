package dev.iaiabot.maze.entity

import dev.iaiabot.maze.entity.decorator.Decorator

class Player(
    private val maze: Maze,
    private val resolver: Resolver,
    private val decorator: Decorator,
) {
    lateinit var currentCell: Cell
    private var moveCounter: Int = 0

    fun start() {
        moveToStartPosition()
        resolver.resolve(this)
        println("move count: $moveCounter")
    }

    fun move(direction: Direction) {
        val xy = direction.calculate(currentCell.xy.x, currentCell.xy.y)
        println("move to $direction ($xy)")
        when (val cell = maze.here(xy)) {
            is Cell.Start, is Cell.Goal, is Cell.Floor -> {
                currentCell = cell
                moveCounter += 1
                decorator.sequentialOutput(currentCell)
            }
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
        currentCell = maze.start
        moveCounter = 0
    }
}

package dev.iaiabot.maze.entity

interface Resolver {
    fun resolve(
        currentCell: () -> Cell,
        currentPosition: () -> XY,
        isGoal: () -> Boolean,
        checkCell: (Direction) -> Cell?,
        move: (Direction) -> Unit,
    )
}

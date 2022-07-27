package dev.iaiabot.maze.entity.decorator

import dev.iaiabot.maze.entity.Cell
import dev.iaiabot.maze.entity.Status

interface Decorator {
    fun onChangeBuildStatus(status: Status, cells: Array<Array<Cell?>>) {}
    fun onChangeResolveStatus(status: Status, cells: Collection<Cell>) {}

    fun outputSequentialBuilding(cell: Cell) {}
    fun outputSequentialResolving(procedures: Collection<Cell>) {}
    fun fullOutput(cells: Array<Array<Cell?>>) {}
}

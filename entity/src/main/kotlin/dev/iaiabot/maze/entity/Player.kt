package dev.iaiabot.maze.entity

import dev.iaiabot.maze.entity.decorator.Decorator
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Player(
    private val maze: Maze,
    private val resolver: Resolver,
    private val decorator: Decorator,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + Job()

    lateinit var currentCell: Cell
    private var moveCounter: Int = 0
    private val procedures = mutableListOf<Cell>()
    private val procedureScores = mutableListOf<Int>()

    fun start(): Job {
        return launch(dispatcher) {
            decorator.onChangeResolveStatus(Status.START_RESOLVE, procedures)
            moveToStartPosition()
            decorator.onChangeResolveStatus(status = Status.RESOLVING, procedures)
            resolver.resolve(this@Player)
            decorator.onChangeResolveStatus(Status.FINISH_RESOLVE, procedures)
        }
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

    fun findShortestPath() {
        decorator.onChangeResolveStatus(Status.START_FIND_SHORTEST_PATH, emptyList())

        val shortestPathFinder = ShortestPathFinder(procedures, procedureScores)

        val shortestPath = shortestPathFinder.find()
        decorator.onChangeResolveStatus(
            Status.FINISH_FIND_SHORTEST_PATH,
            shortestPath,
        )
    }

    private fun moveToStartPosition() {
        procedures.clear()
        procedureScores.clear()
        moveCounter = -1
        move(maze.start)
    }

    private fun move(cell: Cell) {
        currentCell = cell
        moveCounter += 1
        val stepped = procedures.find { it.xy == cell.xy }
        procedures.add((stepped ?: cell).toStep())
        procedureScores.add(moveCounter)
        decorator.outputSequentialResolving(procedures)
    }
}

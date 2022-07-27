package dev.iaiabot.maze.entity

import dev.iaiabot.maze.entity.decorator.Decorator
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Maze(
    private val decorator: Decorator,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + Job()

    val start: Cell
        get() = cells.start!!
    private lateinit var cells: Cells
    private lateinit var generator: Generator

    fun setup(
        width: Int,
        height: Int,
        generator: Generator
    ): Job {
        return launch(dispatcher) {
            decorator.onChangeBuildStatus(Status.SETUP, emptyList())

            cells = Cells(width, height, decorator)
            this@Maze.generator = generator

            generator.setup(cells)
            decorator.onChangeBuildStatus(Status.FINISH_SETUP, cells.dump())
        }
    }

    fun buildMap(): Job {
        return launch(dispatcher) {
            decorator.onChangeBuildStatus(Status.BUILDING, cells.dump())
            generator.buildMap()
            cells.checkGoalAround()
            decorator.onChangeBuildStatus(Status.FINISH_BUILD, cells.dump())
        }
    }

    fun here(xy: XY): Cell? {
        return cells.here(xy)
    }

    fun output() {
        cells.output()
    }
}

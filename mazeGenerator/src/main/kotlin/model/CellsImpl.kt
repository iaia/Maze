package model

import Cell
import Cells
import XY

class CellsImpl(
    private val width: Int,
    private val height: Int,
) : Cells {
    private val cells: Array<Array<Cell?>> = Array(width) {
        arrayOfNulls(height)
    }
    private var wallCount: Int = 0
    override var start: Cell.Start? = null
        private set
    override var goal: Cell.Goal? = null
        private set

    init {
        // 外壁を作る
        (0 until width).forEach { x ->
            add(Cell.Wall(XY(x, 0)))
            add(Cell.Wall(XY(x, height - 1)))
        }
        (1 until height).forEach { y ->
            add(Cell.Wall(XY(0, y)))
            add(Cell.Wall(XY(width - 1, y)))
        }
    }

    override fun add(cell: Cell) {
        when (cells[cell.xy.y][cell.xy.x]) {
            is Cell.Start, is Cell.Goal -> return
            else -> Unit
        }
        when (cell) {
            is Cell.Wall -> {
                wallCount += 1
            }
            is Cell.Start -> {
                start = cell
            }
            is Cell.Goal -> {
                goal = cell
            }
            else -> Unit
        }
        cells[cell.xy.y][cell.xy.x] = cell
    }

    override fun here(x: Int, y: Int): Cell? =
        try {
            cells[y][x]
        } catch (e: ArrayIndexOutOfBoundsException) {
            null
        }

    override fun here(xy: XY): Cell? =
        try {
            cells[xy.y][xy.x]
        } catch (e: ArrayIndexOutOfBoundsException) {
            null
        }

    override fun output() {
        cells.forEach { row ->
            row.forEach { cell ->
                // TODO: decoratorを作って外だしする
                print(
                    when (cell) {
                        is Cell.Start -> "s"
                        is Cell.Floor -> "_"
                        is Cell.Wall -> "x"
                        is Cell.Goal -> "g"
                        else -> "e"
                    }
                )
            }
            println()
        }
    }
}

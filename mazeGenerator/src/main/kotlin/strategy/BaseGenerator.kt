package strategy

import Cell
import Cells
import XY
import kotlin.random.Random

abstract class BaseGenerator(
    protected var width: Int,
    protected var height: Int,
) {
    protected lateinit var cells: Cells

    protected abstract fun buildMap()

    protected fun setStartAndGoal() {
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
}

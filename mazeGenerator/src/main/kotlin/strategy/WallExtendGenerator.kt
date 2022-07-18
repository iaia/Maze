package strategy

import Cell
import Direction
import XY

class WallExtendGenerator : BaseGenerator() {
    private val selectedXY: MutableList<XY> = mutableListOf()

    private val extendDirection: Array<Direction> =
        arrayOf(Direction.LEFT, Direction.BELOW, Direction.ABOVE, Direction.RIGHT)

    private val building: MutableList<XY> = mutableListOf()

    override fun buildMap() {
        setStartAndGoal()
        fillMap()
        selectPositions()

        while (selectedXY.isNotEmpty()) {
            val xy = selectedXY.random()
            building.clear()
            if (cells.here(xy) is Cell.Floor) {
                extendWall(xy)
            }
            selectedXY.remove(xy)
        }
    }

    private fun selectPositions() {
        for (x in 2 until width - 1 step 2) {
            for (y in 2 until height - 1 step 2) {
                val xy = XY(x, y)
                if (cells.start?.xy == xy || cells.goal?.xy == xy) {
                    continue
                }

                selectedXY.add(xy)
            }
        }
    }

    private fun extendWall(
        startXY: XY,
        except: Array<Direction> = emptyArray()
    ) {
        // 開始位置をまず壁にする
        val cell0 = cells.here(startXY)
        if (cell0 == null || cell0 is Cell.Start || cell0 is Cell.Goal) {
            throw Exception()
        }
        if (cell0 is Cell.Floor) {
            cells.add(Cell.Wall(cell0.xy))
            building.add(cell0.xy)
        }
        // 上下左右のいずれかの座標を取り出す ただし、上下左右壁が伸ばせない場合は終了する
        val direction = randomDirection(except) ?: return
        val cell1 = cells.here(direction.calculate(startXY))
        // その座標が通路なら、更に1個その方向の座標を取り出す
        if (cell1 is Cell.Floor) {
            when (val cell2 = cells.here(direction.calculate(cell1.xy))) {
                is Cell.Start, is Cell.Goal -> {
                    extendWall(startXY, except)
                }
                is Cell.Wall -> {
                    if (building.contains(cell2.xy)) {
                        // その座標が壁伸ばし中の壁ならその方向には建設せずに、別の座標を選ぶ (つまり戻って別座標に向かって壁を建設する)
                        extendWall(startXY, except + direction)
                    } else {
                        // その座標が既存の壁なら、1個目まで建設して終了する
                        cells.add(Cell.Wall(cell1.xy))
                        building.add(cell1.xy)
                        return
                    }
                }
                is Cell.Floor -> {
                    // 床なら壁を2個建設して更に末端から延長する
                    cells.add(Cell.Wall(cell1.xy))
                    building.add(cell1.xy)
                    cells.add(Cell.Wall(cell2.xy))
                    building.add(cell2.xy)
                    extendWall(cell2.xy)
                }
                else -> {
                    throw Exception()
                }
            }
        } else {
            // 床以外なら別の方向で建設をする
            extendWall(startXY, except + direction)
        }
    }

    private fun fillMap() {
        (1 until height - 1).forEach { y ->
            (1 until width - 1).forEach { x ->
                cells.add(Cell.Floor(XY(x, y)))
            }
        }
    }

    private fun randomDirection(except: Array<Direction>): Direction? {
        return extendDirection.filterNot {
            except.contains(it)
        }.randomOrNull()
    }
}

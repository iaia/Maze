package dev.iaiabot.maze.entity

class ShortestPathFinder(
    procedures: List<Cell>,
    scores: List<Int>,
) {
    private val proceduresAndScores: List<Pair<Cell, Int>>
    private val shortestPath = mutableListOf<Cell>()

    init {
        if (procedures.size != scores.size) {
            throw Exception("size mismatch ${procedures.size}, ${scores.size}")
        }

        proceduresAndScores = procedures.mapIndexed { index, cell ->
            Pair(cell, scores[index])
        }
    }

    fun find(decorate: (List<Cell>) -> Unit): List<Cell> {
        var neighbor: Cell? = proceduresAndScores.findLast {
            val cell = it.first
            if (cell is Cell.Stepped) {
                cell.origin is Cell.Goal
            } else {
                false
            }
        }?.first

        shortestPath.add(neighbor ?: throw Exception())

        while (neighbor != null) {
            neighbor = findShortestNeighbor(neighbor)
            if (neighbor != null) {
                shortestPath.add(neighbor)
                decorate(shortestPath)
                if (neighbor is Cell.Stepped && neighbor.origin is Cell.Start) {
                    break
                }
            } else {
                throw Exception()
            }
        }

        return shortestPath
    }

    private fun findShortestNeighbor(cell: Cell): Cell? {
        val left = Direction.LEFT.calculate(cell.xy)
        val right = Direction.RIGHT.calculate(cell.xy)
        val above = Direction.ABOVE.calculate(cell.xy)
        val below = Direction.BELOW.calculate(cell.xy)

        val leftCellScore = proceduresAndScores.findLast {
            it.first.xy == left
        }
        val rightCellScore = proceduresAndScores.findLast {
            it.first.xy == right
        }
        val aboveCellScore = proceduresAndScores.findLast {
            it.first.xy == above
        }
        val belowCellScore = proceduresAndScores.findLast {
            it.first.xy == below
        }

        val shortestPath = listOf(
            leftCellScore,
            rightCellScore,
            aboveCellScore,
            belowCellScore,
        ).minByOrNull { it?.second ?: Int.MAX_VALUE }

        return shortestPath?.first
    }
}

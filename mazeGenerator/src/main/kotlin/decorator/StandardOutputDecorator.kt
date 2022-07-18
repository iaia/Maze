package decorator

import Cell

class StandardOutputDecorator(
    private val sequentialOutput: Boolean,
) : Decorator {
    override fun sequentialOutput(cell: Cell) {
        if (sequentialOutput) {
            println(cell.toString())
        }
    }

    override fun fullOutput(cells: Array<Array<Cell?>>) {
        cells.forEach { row ->
            row.forEach { cell ->
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

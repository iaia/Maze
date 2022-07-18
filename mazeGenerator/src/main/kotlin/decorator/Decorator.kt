package decorator

import Cell

interface Decorator {
    fun sequentialOutput(cell: Cell)
    fun fullOutput(cells: Array<Array<Cell?>>)
}

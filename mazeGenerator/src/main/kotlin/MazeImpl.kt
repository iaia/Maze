internal class MazeImpl(
    override val cells: Array<Array<Cell>>,
    override val start: XY,
    override val goal: XY
) : Maze {

    override fun here(xy: XY): Cell {
        return cells[xy.y][xy.x]
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
                    }
                )
            }
            println()
        }
    }
}

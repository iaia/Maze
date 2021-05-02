internal class MazeImpl(
    private val cells: Cells,
) : Maze {

    override fun start(): Cell.Start = cells.start!!

    override fun goal(): Cell.Goal = cells.goal!!

    override fun here(xy: XY): Cell {
        return cells.here(xy)
    }

    override fun output() {
        cells.output()
    }
}

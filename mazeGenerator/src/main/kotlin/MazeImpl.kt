internal class MazeImpl(
    override val cells: Array<Array<Cell>>,
    override val start: XY,
    override val goal: XY
) : Maze

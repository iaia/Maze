internal class MazeImpl(
    override val cells: Array<Array<Cell>>,
    override val start: Pair<Int, Int>,
    override val goal: Pair<Int, Int>
) : Maze

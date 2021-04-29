class Player(
    private val maze: Maze,
    private val resolver: Resolver,
) {
    private var x: Int = 0
    private var y: Int = 0

    fun start() {
        moveToStartPosition()
        resolver.resolve()
    }

    private fun moveToStartPosition() {
        move(maze.start)
    }

    private fun move(xy: XY) {
        move(xy.x, xy.y)
    }

    private fun move(x: Int, y: Int) {
    }

    private fun isGoal(): Boolean =
        x == maze.goal.x && y == maze.goal.y
}

class Player(
    private val maze: Maze,
    private val resolver: Resolver,
) {
    private var x: Int = 0
    private var y: Int = 0

    fun start() {
        moveToStartPosition()
        resolver.resolve(this)
    }

    fun move(xy: XY) {
        when (maze.here(xy)) {
            is Cell.Start, is Cell.Floor -> {
                this.x = xy.x
                this.y = xy.y
            }
            is Cell.Wall -> return
            is Cell.Goal -> return
        }
    }

    fun isGoal(): Boolean =
        x == maze.goal.x && y == maze.goal.y

    private fun moveToStartPosition() {
        move(maze.start)
    }
}

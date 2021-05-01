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
        println("move to $xy")
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

    fun currentPosition() = XY(x, y)

    fun canGo(xy: XY): Boolean {
        return maze.here(xy) != Cell.Wall
    }

    private fun moveToStartPosition() {
        move(maze.start)
    }
}

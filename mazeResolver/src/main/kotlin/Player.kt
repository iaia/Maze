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

    fun move(direction: Direction) {
        val xy = direction.calculate(x, y)
        println("move to $direction ($xy)")
        when (maze.here(xy)) {
            is Cell.Start, is Cell.Goal, is Cell.Floor -> {
                this.x = xy.x
                this.y = xy.y
            }
            is Cell.Wall -> return
        }
    }

    fun isGoal(): Boolean = maze.here(currentPosition()) is Cell.Goal

    fun currentPosition() = XY(x, y)

    fun checkCell(direction: Direction): Cell {
        return maze.here(direction.calculate(x, y))
    }

    private fun moveToStartPosition() {
        val start = maze.start()
        x = start.xy.x
        y = start.xy.y
    }
}

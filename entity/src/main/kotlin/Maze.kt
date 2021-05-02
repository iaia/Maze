interface Maze {
    fun start(): Cell.Start
    fun goal(): Cell.Goal
    fun here(xy: XY): Cell
    fun output()
}

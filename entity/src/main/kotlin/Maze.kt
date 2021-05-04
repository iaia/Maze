interface Maze {
    val start: Cell.Start
    val goal: Cell.Goal

    fun here(xy: XY): Cell?
    fun output()
}

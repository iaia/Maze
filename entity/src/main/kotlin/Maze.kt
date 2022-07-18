interface Maze {
    val start: Cell

    fun setup()
    fun buildMap()
    fun here(xy: XY): Cell?
    fun output()
}

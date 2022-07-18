interface Cells {
    val width: Int
    val height: Int
    val start: Cell.Start?
    val goal: Cell.Goal?

    fun add(cell: Cell)
    fun here(x: Int, y: Int): Cell?
    fun here(xy: XY): Cell?
    fun output()
}

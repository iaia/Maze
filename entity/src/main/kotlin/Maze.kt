interface Maze {
    val cells: Array<Array<Cell>>
    val start: XY
    val goal: XY

    fun here(xy: XY): Cell
}

@JvmInline
value class XY(private val xy: Pair<Int, Int>) {
    constructor(x: Int, y: Int) : this(
        xy = Pair(x, y)
    )

    val x: Int
        get() = xy.first
    val y: Int
        get() = xy.second
}

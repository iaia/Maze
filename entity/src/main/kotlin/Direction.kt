enum class Direction(
    private val x: Int,
    private val y: Int
) {
    LEFT(-1, 0),
    RIGHT(1, 0),
    ABOVE(0, -1),
    BELOW(0, 1),
    STOP(0, 0),
    ;

    fun calculate(x: Int, y: Int): XY = XY(x + this.x, y + this.y)
    fun calculate(xy: XY): XY = XY(xy.x + this.x, xy.y + this.y)
}

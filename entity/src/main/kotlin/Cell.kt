sealed class Cell {
    abstract val xy: XY

    class Floor(override val xy: XY) : Cell()
    class Wall(override val xy: XY) : Cell()
    class Start(override val xy: XY) : Cell()
    class Goal(override val xy: XY) : Cell()

    override fun toString(): String {
        return "x=${xy.x}, y=${xy.y}"
    }
}

package dev.iaiabot.maze.entity

class XY(private val xy: Pair<Int, Int>) {
    constructor(x: Int, y: Int) : this(
        xy = Pair(x, y)
    )

    override fun toString(): String {
        return "x=$x, y=$y"
    }

    override fun equals(other: Any?): Boolean {
        return if (other is XY) {
            other.x == this.x && other.y == this.y
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return (x * 1000) + y
    }

    val x: Int
        get() = xy.first
    val y: Int
        get() = xy.second
}

package dev.iaiabot.maze.entity

sealed class Cell {
    abstract val xy: XY
    val x: Int
        get() = xy.x
    val y: Int
        get() = xy.y

    class Floor(override val xy: XY) : Cell() {
        override fun toString(): String {
            return super.toString() + ", Floor"
        }
    }

    class Wall(override val xy: XY) : Cell() {
        override fun toString(): String {
            return super.toString() + ", Wall"
        }
    }

    class Start(override val xy: XY) : Cell() {
        override fun toString(): String {
            return super.toString() + ", Start"
        }
    }

    class Goal(override val xy: XY) : Cell() {
        override fun toString(): String {
            return super.toString() + ", Goal"
        }
    }

    override fun toString(): String {
        return "[x=${xy.x}, y=${xy.y}]"
    }
}

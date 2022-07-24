package dev.iaiabot.maze.entity

sealed class Cell {
    abstract val xy: XY
    val x: Int
        get() = xy.x
    val y: Int
        get() = xy.y
    var stepped: Int = 0
        private set

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
        return "[x=${xy.x}, y=${xy.y}], step=${stepped}"
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Cell) {
            other.x == x && other.y == y && other.stepped == stepped && other.javaClass == this.javaClass
        } else {
            false
        }
    }

    fun step() {
        stepped += 1
    }
}

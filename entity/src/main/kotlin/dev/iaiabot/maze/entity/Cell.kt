package dev.iaiabot.maze.entity

sealed class Cell {
    abstract val xy: XY
    val x: Int
        get() = xy.x
    val y: Int
        get() = xy.y

    class Empty(override val xy: XY) : Cell() {
        override fun toString(): String {
            return "[Empty]"
        }
    }

    class Floor(override val xy: XY) : Cell() {
        override fun toString(): String {
            return super.toString() + "[Floor]"
        }
    }

    class Wall(override val xy: XY) : Cell() {
        override fun toString(): String {
            return super.toString() + "[Wall]"
        }
    }

    class Start(override val xy: XY) : Cell() {
        override fun toString(): String {
            return super.toString() + "[Start]"
        }
    }

    class Goal(override val xy: XY) : Cell() {
        override fun toString(): String {
            return super.toString() + "[Goal]"
        }
    }

    class Stepped internal constructor(
        override val xy: XY,
        val origin: Cell,
    ) : Cell() {
        override fun toString(): String {
            return super.toString() + "[Stepped]"
        }

        override fun toStep() = this

        var stepped: Int = 0
            private set

        internal fun step() {
            stepped += 1
        }
    }

    override fun toString(): String {
        return "[x=${xy.x}, y=${xy.y}]"
    }

    internal open fun toStep() = Stepped(
        xy,
        this
    ).also {
        it.step()
    }
}

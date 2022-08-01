package dev.iaiabot.maze.entity

sealed class Cell {
    abstract val xy: XY
    val x: Int
        get() = xy.x
    val y: Int
        get() = xy.y
    var stepped: Int = 0
        private set

    override fun toString(): String {
        return "[x=${xy.x}, y=${xy.y}]"
    }

    internal fun step() {
        stepped += 1
    }

    internal open fun toStep() = Stepped(
        xy,
        this
    ).also {
        it.step()
    }

    internal open fun toShortest() = Shortest(
        xy,
        if (this is Stepped) {
            this.origin
        } else {
            this
        },
    )

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
            return super.toString() + "[Stepped:${stepped}]"
        }

        override fun toStep() = this.also {
            it.step()
        }
    }

    class Shortest internal constructor(
        override val xy: XY,
        val origin: Cell,
    ) : Cell() {
        override fun toString(): String {
            return super.toString() + "[Shortest]"
        }

        override fun toStep(): Stepped {
            throw UnsupportedOperationException()
        }

        override fun toShortest(): Shortest {
            throw UnsupportedOperationException()
        }
    }
}

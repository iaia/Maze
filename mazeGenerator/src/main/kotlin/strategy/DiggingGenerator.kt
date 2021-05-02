package strategy

import Generator
import Maze
import model.CellsImpl
import model.MazeImpl

class DiggingGenerator(
    width: Int,
    height: Int,
) : Generator, BaseGenerator(width, height) {

    override fun generate(): Maze {
        val cells = CellsImpl(width, height)

        return MazeImpl(cells)
    }

    override fun buildMap() {
    }
}

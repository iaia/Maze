package strategy

import dev.iaiabot.maze.entity.Generator
import dev.iaiabot.maze.mazegenerator.strategy.WallExtendGenerator

internal class WallExtendGeneratorTest : DescribeSpec({
    describe("5x5") {
        val generator: Generator = WallExtendGenerator(5, 5)

        it("テスト") {
            val maze = generator.generate()
            maze.output()
        }
    }
})

package strategy

import Generator

internal class WallExtendGeneratorTest : DescribeSpec({
    describe("5x5") {
        val generator: Generator = WallExtendGenerator(5, 5)

        it("テスト") {
            val maze = generator.generate()
            maze.output()
        }
    }
})

import model.MazeImpl
import strategy.SampleGenerator

fun main() {
    // TODO: width/height はランダムな奇数にする
    // val maze = SampleGenerator(5, 5).generate()
    // val maze = LayPillarGenerator(9, 9).generate()
    // val maze = DiggingGenerator(15, 15).generate()
    //val maze = WallExtendGenerator(15, 15).generate()
    val maze = MazeImpl.generate(
        width = 5,
        height = 5,
        generator = SampleGenerator(),
        sequentialOutput = true,
    )
    println("------------------ setup")
    maze.setup()
    println("------------------ generate")
    maze.buildMap()
    println("------------------ output")
    maze.output()

    // val resolver = SampleResolver()
    // val resolver = RightHandResolver()
    //val resolver = TremorResolver()
    //val player = Player(maze, resolver)
    //player.start()

    //println("result: ${player.isGoal()}")
}

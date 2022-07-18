import model.MazeImpl
import strategy.DiggingGenerator

fun main() {
    // TODO: width/height はランダムな奇数にする
    val maze = MazeImpl.generate(
        width = 5,
        height = 5,
        generator = DiggingGenerator(),
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

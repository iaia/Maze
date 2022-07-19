import dev.iaiabot.maze.mazegenerator.decorator.StandardOutputDecorator
import dev.iaiabot.maze.mazegenerator.model.MazeImpl
import dev.iaiabot.maze.mazegenerator.strategy.DiggingGenerator

fun main() {
    // TODO: width/height はランダムな奇数にする
    val maze = MazeImpl.generate(
        width = 7,
        height = 7,
        generator = DiggingGenerator(),
        decorator = StandardOutputDecorator(sequentialOutput = true),
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

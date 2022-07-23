import dev.iaiabot.maze.entity.Maze
import dev.iaiabot.maze.entity.Player
import dev.iaiabot.maze.entity.decorator.StandardOutputDecorator
import dev.iaiabot.maze.mazegenerator.strategy.DiggingGenerator
import dev.iaiabot.maze.mazeresolver.strategy.RightHandResolver

fun main() {
    // TODO: width/height はランダムな奇数にする
    val maze = Maze(
        width = 71,
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
    val resolver = RightHandResolver()
    //val resolver = TremorResolver()
    val player = Player(maze, resolver, decorator = StandardOutputDecorator(sequentialOutput = true))
    player.start()

    //println("result: ${player.isGoal()}")
}

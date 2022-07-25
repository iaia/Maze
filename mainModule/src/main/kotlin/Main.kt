import dev.iaiabot.maze.entity.Maze
import dev.iaiabot.maze.entity.Player
import dev.iaiabot.maze.entity.decorator.StandardOutputDecorator
import dev.iaiabot.maze.mazegenerator.strategy.DiggingGenerator
import dev.iaiabot.maze.mazeresolver.strategy.RightHandResolver

fun main() {
    // TODO: width/height はランダムな奇数にする
    val maze = Maze(
        decorator = StandardOutputDecorator(sequentialOutput = true),
    )
    maze.setup(
        width = 33,
        height = 11,
        generator = DiggingGenerator()
    )
    maze.buildMap()
    maze.output()

    // val resolver = SampleResolver()
    val resolver = RightHandResolver()
    //val resolver = TremorResolver()
    val player = Player(maze, resolver, decorator = StandardOutputDecorator(sequentialOutput = true))
    // player.start()

    //println("result: ${player.isGoal()}")
}

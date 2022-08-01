import dev.iaiabot.maze.entity.Generator
import dev.iaiabot.maze.entity.Maze
import dev.iaiabot.maze.entity.Player
import dev.iaiabot.maze.entity.decorator.StandardOutputDecorator
import dev.iaiabot.maze.mazegenerator.strategy.DiggingGenerator
import dev.iaiabot.maze.mazeresolver.strategy.RightHandResolver
import kotlinx.coroutines.runBlocking

suspend fun main() {
    // TODO: width/height はランダムな奇数にする
    val maze = Maze(
        decorator = StandardOutputDecorator(sequentialOutput = false),
    )
    val diggingGenerator = DiggingGenerator(priority = DiggingGenerator.Priority.RANDOM)

    suspend fun start(generator: Generator) {
        maze.setup(
            width = 33,
            height = 9,
            generator = generator
        ).join()
        maze.buildMap().join()
        maze.output()
    }

    runBlocking {
        repeat(3) {
            start(diggingGenerator)
        }
        start(DiggingGenerator(priority = DiggingGenerator.Priority.DEPTH_FIRST))
        start(DiggingGenerator(priority = DiggingGenerator.Priority.BREADTH_FIRST))
    }

    // val resolver = SampleResolver()
    val resolver = RightHandResolver()
    //val resolver = TremorResolver()
    val player = Player(maze, resolver, decorator = StandardOutputDecorator(sequentialOutput = true))

    runBlocking {
        player.start().join()
    }

    player.findShortestPath().join()

    //println("result: ${player.isGoal()}")
}

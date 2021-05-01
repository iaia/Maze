import strategy.LayPillarGenerator
import strategy.SampleResolver

fun main() {
    // val maze = SampleGenerator.generate(5, 5)
    val maze = LayPillarGenerator().generate(12, 12)
    maze.output()
    println("start x=${maze.start.x}, y=${maze.start.y}")
    println("goal x=${maze.goal.x}, y=${maze.goal.y}")
    val resolver = SampleResolver()
    val player = Player(maze, resolver)
    player.start()

    println("result: ${player.isGoal()}")
}

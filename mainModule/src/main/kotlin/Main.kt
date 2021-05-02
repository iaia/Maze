import strategy.LayPillarGenerator
import strategy.SampleResolver

fun main() {
    // val maze = SampleGenerator.generate(5, 5)
    val maze = LayPillarGenerator().generate(5, 5)
    maze.output()
    println("start ${maze.start()}")
    println("goal ${maze.goal()}")
    val resolver = SampleResolver()
    val player = Player(maze, resolver)
    player.start()

    println("result: ${player.isGoal()}")
}

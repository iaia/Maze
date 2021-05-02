import strategy.LayPillarGenerator
import strategy.RightHandResolver

fun main() {
    // val maze = SampleGenerator(5, 5).generate()
    val maze = LayPillarGenerator(7, 7).generate()
    maze.output()
    println("start ${maze.start()}")
    println("goal ${maze.goal()}")
    // val resolver = SampleResolver()
    val resolver = RightHandResolver()
    val player = Player(maze, resolver)
    player.start()

    println("result: ${player.isGoal()}")
}

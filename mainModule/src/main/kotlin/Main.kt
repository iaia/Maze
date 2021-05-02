import strategy.LayPillarGenerator
import strategy.RightHandResolver

fun main() {
    // TODO: width/height はランダムな奇数にする
    // val maze = SampleGenerator(5, 5).generate()
    val maze = LayPillarGenerator(15, 15).generate()
    maze.output()
    println("start ${maze.start()}")
    println("goal ${maze.goal()}")
    // val resolver = SampleResolver()
    val resolver = RightHandResolver()
    val player = Player(maze, resolver)
    player.start()

    println("result: ${player.isGoal()}")
}

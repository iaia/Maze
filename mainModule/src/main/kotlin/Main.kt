import strategy.SampleGenerator
import strategy.SampleResolver

fun main() {
    val maze = SampleGenerator.generate()
    maze.output()
    val resolver = SampleResolver()
    val player = Player(maze, resolver)
    player.start()
}

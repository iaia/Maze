interface Maze {
    val cells: Array<Array<Cell>>
    val start: Pair<Int, Int>
    val goal: Pair<Int, Int>
}

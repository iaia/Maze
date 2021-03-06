package dev.iaiabot.maze.entity

enum class Status {
    INIT,
    START_SETUP,
    FINISH_SETUP,
    BUILDING,
    FINISH_BUILD,
    START_RESOLVE,
    RESOLVING,
    FINISH_RESOLVE,
    START_FIND_SHORTEST_PATH,
    FINISH_FIND_SHORTEST_PATH,
}

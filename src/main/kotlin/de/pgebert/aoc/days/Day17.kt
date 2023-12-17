package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import java.util.*


class Day17(input: String? = null) : Day(17, "Day17", input) {


    data class Node(val x: Int, val y: Int, val direction: Direction, val directionCount: Int)

    enum class Direction { NORTH, SOUTH, EAST, WEST }

    private val map = inputList.map { line -> line.map { it.digitToInt() } }

    override fun partOne() = findPathOfMinimalLoss(maxConsecutiveBlocks = 3)

    override fun partTwo() = findPathOfMinimalLoss(minConsecutiveBlocks = 4, maxConsecutiveBlocks = 10)

    private fun findPathOfMinimalLoss(
        minConsecutiveBlocks: Int = 0,
        maxConsecutiveBlocks: Int = Int.MAX_VALUE
    ): Int {
        val losses = mutableMapOf<Node, Int>().withDefault { Int.MAX_VALUE }
        val predecessors = mutableMapOf<Node, Node>()

        val compareByLoss: Comparator<Node> = compareBy { losses.getValue(it) }
        val queue = PriorityQueue<Node>(compareByLoss)

        listOf(
            Node(0, 0, Direction.EAST, 1),
            Node(0, 0, Direction.SOUTH, 1)
        ).forEach {
            losses[it] = 0
            queue += it
        }

        while (queue.isNotEmpty()) {
            val current = queue.remove()

            val neighbours = buildList {
                if (current.directionCount < minConsecutiveBlocks) {
                    current.moveInto(current.direction)?.also { add(it) }
                } else {
                    Direction.values().forEach { direction ->
                        current.moveInto(direction)
                            ?.takeIf { it.directionCount <= maxConsecutiveBlocks }
                            ?.also { add(it) }
                    }
                }
            }

            neighbours.forEach { neighbour ->
                val alternative = losses.getValue(current) + map[neighbour.x][neighbour.y]
                if (alternative < losses.getValue(neighbour)) {
                    losses[neighbour] = alternative
                    predecessors[neighbour] = current
                    queue.add(neighbour)
                }
            }
        }

        return losses.filterKeys { it.x == map.size - 1 && it.y == map[map.size - 1].size - 1 }.minOf { it.value }
    }


    private fun Node.moveInto(direction: Direction): Node? {

        val isOppositeDirection = when (direction) {
            Direction.NORTH -> this.direction == Direction.SOUTH
            Direction.SOUTH -> this.direction == Direction.NORTH
            Direction.EAST -> this.direction == Direction.WEST
            Direction.WEST -> this.direction == Direction.EAST
        }

        if (isOppositeDirection) return null

        val (newX, newY) = when (direction) {
            Direction.NORTH -> Pair(x - 1, y)
            Direction.SOUTH -> Pair(x + 1, y)
            Direction.EAST -> Pair(x, y + 1)
            Direction.WEST -> Pair(x, y - 1)
        }

        if (newX !in map.indices || newY !in map[newX].indices) return null

        val newDirectionCount = if (direction == this.direction) directionCount + 1 else 1

        return Node(newX, newY, direction, newDirectionCount)
    }
}

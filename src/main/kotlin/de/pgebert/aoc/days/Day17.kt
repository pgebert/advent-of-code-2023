package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import de.pgebert.aoc.NOT_IMPLEMENTED
import java.util.*


class Day17(input: String? = null) : Day(17, "Day17", input) {


    data class Point(val x: Int, val y: Int)


    private val map = inputList.map { line -> line.map { it.digitToInt() } }

    override fun partOne(): Int {

        val losses = mutableMapOf<Point, Int>().withDefault { Int.MAX_VALUE }
        val predecessors = mutableMapOf<Point, Point>()


        val compareByLoss: Comparator<Point> = compareBy { losses.getValue(it) }
        val queue = PriorityQueue<Point>(compareByLoss)

        losses[Point(0, 0)] = 0

        for (x in map.indices) {
            for (y in map[x].indices) {
                queue += Point(x, y)
            }
        }

        while (queue.isNotEmpty()) {
            val current = queue.remove()

            val neighbours = buildList<Point> {
                if (current.x + 1 < map.size && predecessors.from(current).take(3).count { it.y == current.y } < 3) {
                    add(Point(current.x + 1, current.y))
                }
                if (current.x - 1 >= 0 && predecessors.from(current).take(3).count { it.y == current.y } < 3) {
                    add(Point(current.x - 1, current.y))
                }
                if (current.y + 1 < map[current.x].size && predecessors.from(current).take(3)
                        .count { it.x == current.x } < 3
                ) {
                    add(Point(current.x, current.y + 1))
                }
                if (current.y - 1 >= 0 && predecessors.from(current).take(3).count { it.x == current.x } < 3) {
                    add(Point(current.x, current.y - 1))
                }
            }

            neighbours.filter { it in queue }.forEach { neighbour ->
                val alternative = losses.getValue(current) + map[neighbour.x][neighbour.y]
                if (alternative < losses.getValue(neighbour)) {
                    losses[neighbour] = alternative
                    predecessors[neighbour] = current
                    queue.update(neighbour)
                }
            }

//            with(current) {
//                if (x + 1 < map.size
//                    && losses.getValue(Point(x, y)) + map[x + 1][y] < losses.getValue(Point(x + 1, y))
//                    && predecessors.getPredecessors(current).take(3).count { it.y == y } < 3
//                ) {
//                    losses[Point(x + 1, y)] = losses.getValue(Point(x, y)) + map[x + 1][y]
//                    queue.add(Point(x + 1, y))
//                    predecessors[Point(x + 1, y)] = current
//                }
//                if (x - 1 >= 0
//                    && losses.getValue(Point(x, y)) + map[x - 1][y] < losses.getValue(Point(x - 1, y))
//                    && predecessors.getPredecessors(current).take(3).count { it.y == y } < 3
//                ) {
//                    losses[Point(x - 1, y)] = losses.getValue(Point(x, y)) + map[x - 1][y]
//                    queue.add(Point(x - 1, y))
//                    predecessors[Point(x - 1, y)] = current
//                }
//                if (y + 1 < map[x].size
//                    && losses.getValue(Point(x, y)) + map[x][y + 1] < losses.getValue(Point(x, y + 1))
//                    && predecessors.getPredecessors(current).take(3).count { it.x == x } < 3
//                ) {
//                    losses[Point(x, y + 1)] = losses.getValue(Point(x, y)) + map[x][y + 1]
//                    queue.add(Point(x, y + 1))
//                    predecessors[Point(x, y + 1)] = current
//
//                }
//                if (y - 1 >= 0
//                    && losses.getValue(Point(x, y)) + map[x][y - 1] < losses.getValue(Point(x, y - 1))
//                    && predecessors.getPredecessors(current).take(3).count { it.x == x } < 3
//                ) {
//                    losses[Point(x, y - 1)] = losses.getValue(Point(x, y)) + map[x][y - 1]
//                    queue.add(Point(x, y - 1))
//                    predecessors[Point(x, y - 1)] = current
//                }
//            }
        }

        buildString {
            val visited = predecessors.from(Point(map.size - 1, map[map.size - 1].size - 1))

            for (x in map.indices) {
                for (y in map[x].indices) {
                    if (Point(x, y) in visited) append("X")
                    else append(map[x][y])
                }
                append("\n")
            }
        }.also { print(it) }
        print("\n")
        buildString {
            val visited = predecessors.from(Point(map.size - 1, map[map.size - 1].size - 1))

            for (x in map.indices) {
                for (y in map[x].indices) {
                    if (Point(x, y) in visited) append(losses[Point(x, y)])
                    else append(".")
                }
                append("\n")
            }
        }.also { print(it) }

        return losses.getValue(Point(map.size - 1, map[map.size - 1].size - 1))
    }

    fun MutableMap<Point, Point>.from(point: Point): List<Point> {

        var current: Point? = point
        var predecessors = mutableListOf<Point>()

        while (current != null) {
            predecessors.add(current)
            current = this[current]
        }
        return predecessors
    }

    override fun partTwo() = NOT_IMPLEMENTED
}

private fun <E> PriorityQueue<E>.update(element: E) {
    remove(element)
    add(element)
}

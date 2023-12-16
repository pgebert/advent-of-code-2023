package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import de.pgebert.aoc.NOT_IMPLEMENTED

class Day16(input: String? = null) : Day(16, "The Floor Will Be Lava", input) {


    data class Point(val x: Int, val y: Int) {

        fun plus(other: Point) = Point(x + other.x, y + other.y)

    }

    data class Beam(val position: Point, val direction: Point)

    data class Obstacle(val position: Point, val character: Char)

    override fun partOne(): Int {

        val visited = mutableSetOf<Beam>()

        val obstacles = mutableListOf<Obstacle>()

        for (x in inputList.indices) {
            for (y in inputList[x].indices) {
                val char = inputList[x][y]
                if (char != '.') {
                    obstacles.add(Obstacle(position = Point(x, y), character = char))
                }
            }
        }

        val queue = ArrayDeque<Beam>()
        queue += Beam(position = Point(0, -1), direction = Point(0, 1))

        while (queue.isNotEmpty()) {
            val beam = queue.removeFirst()

            val newPosition = beam.position.plus(beam.direction)

            if (newPosition.x !in inputList.indices || newPosition.y !in inputList.first().indices) continue

            val newDirections: List<Point> = obstacles
                .firstOrNull { obstacle -> obstacle.position == newPosition }
                ?.let { (_, character) ->
                    buildList {
                        when (character) {
                            '/' -> add(Point(-beam.direction.y, -beam.direction.x))
                            '\\' -> add(Point(beam.direction.y, beam.direction.x))
                            '|' -> {
                                add(Point(-beam.direction.y, 0)) // split 1
                                add(Point(beam.direction.y, 0))  // split 2
                                add(Point(beam.direction.x, 0)) // former direction
                            }

                            '-' -> {
                                add(Point(0, -beam.direction.x)) // split 1
                                add(Point(0, beam.direction.x))  // split 2
                                add(Point(0, beam.direction.y)) // former direction
                            }
                        }
                    }
                }
                ?.filterNot { it == Point(0, 0) }
                ?: listOf(beam.direction) // former direction

            newDirections.forEach { newDirection ->
                Beam(position = newPosition, direction = newDirection)
                    .takeUnless { it in visited }
                    ?.also {
                        queue.add(it)
                        visited.add(it)
                    }
            }

        }

        return visited.map { it.position }.toSet().size
    }

    override fun partTwo() = NOT_IMPLEMENTED
}

package de.pgebert.aoc.days

import de.pgebert.aoc.Day


class Day14(input: String? = null) : Day(14, "Parabolic Reflector Dish", input) {


    data class Point(val x: Int, val y: Int)

    private var cubicStones = setOf<Point>()
    private var roundStones = setOf<Point>()

    init {
        parseStones()
    }

    override fun partOne() = roundStones.shiftNorth().sumOf { stone -> inputList.size - stone.x }

    private fun Set<Point>.shiftNorth() =
        sortedBy { it.x }.fold(setOf<Point>()) { shifted, (x, y) ->
            val newX = (listOf(Point(-1, y)) + cubicStones + shifted)
                .filter { it.y == y && it.x < x }
                .maxOf { it.x + 1 }

            shifted + Point(newX, y)
        }

    private fun Set<Point>.shiftSouth() =
        sortedByDescending { it.x }.fold(setOf<Point>()) { shifted, (x, y) ->
            val newX = (listOf(Point(inputList.size, y)) + cubicStones + shifted)
                .filter { it.y == y && it.x > x }
                .minOf { it.x - 1 }

            shifted + Point(newX, y)
        }

    private fun Set<Point>.shiftEast() =
        sortedByDescending { it.y }.fold(setOf<Point>()) { shifted, (x, y) ->
            val newY = (listOf(Point(x, inputList.first().length)) + cubicStones + shifted)
                .filter { it.x == x && it.y > y }
                .minOf { it.y - 1 }

            shifted + Point(x, newY)
        }

    private fun Set<Point>.shiftWest() =
        sortedBy { it.y }.fold(setOf<Point>()) { shifted, (x, y) ->
            val newY = (listOf(Point(x, -1)) + cubicStones + shifted)
                .filter { it.x == x && it.y < y }
                .maxOf { it.y + 1 }

            shifted + Point(x, newY)
        }

    private fun parseStones() {

        val cubic = mutableListOf<Point>()
        val round = mutableListOf<Point>()

        inputList.forEachIndexed { x, line ->
            line.forEachIndexed { y, char ->
                if (char == '#') cubic.add(Point(x, y))
                else if (char == 'O') round.add(Point(x, y))
            }
        }

        cubicStones = cubic.toSet()
        roundStones = round.toSet()
    }

    override fun partTwo(): Int {

        var shifted = roundStones

        for (i in 0..<1000000000) {
            val next = shifted.shiftNorth().shiftWest().shiftSouth().shiftEast()
            if (next == shifted) break
            shifted = next

        }

//        buildString {
//            inputList.forEachIndexed { x, line ->
//                line.forEachIndexed { y, char ->
//                    if (cubicStones.any { it.first == x && it.second == y }) append("#")
//                    else if (shifted.any { it.first == x && it.second == y }) append("O")
//                    else append(".")
//                }
//                append("\n")
//            }
//        }.also { print(it) }


        return shifted.sumOf { stone -> inputList.size - stone.x }
    }
}

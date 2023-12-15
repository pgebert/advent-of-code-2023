package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import de.pgebert.aoc.NOT_IMPLEMENTED

private typealias Point = Pair<Int, Int>

class Day14(input: String? = null) : Day(14, "Parabolic Reflector Dish", input) {

    override fun partOne(): Int {

        val cubicStones = mutableListOf<Point>()
        val roundStones = mutableListOf<Point>()
        val shifted = mutableListOf<Point>()

        inputList.forEachIndexed { x, line ->
            line.forEachIndexed { y, char ->
                if (char == '#') cubicStones.add(Point(x, y))
                else if (char == 'O') roundStones.add(Point(x, y))
            }
        }

        roundStones.sortedBy { it.first }.forEach { stone ->
            val newX = (listOf(0)
                    + cubicStones.filter { it.second == stone.second && it.first < stone.first }.map { it.first + 1 }
                    + shifted.filter { it.second == stone.second && it.first < stone.first }.map { it.first + 1 })
                .max()

            val new = Point(newX, stone.second)

            shifted.add(new)
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

        return shifted.sumOf { stone -> inputList.size - stone.first }
    }

    override fun partTwo() = NOT_IMPLEMENTED
}

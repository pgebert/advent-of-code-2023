package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day09(input: String? = null) : Day(9, "Mirage Maintenance", input) {

    override fun partOne() = inputList.sumOf { line ->
        line.split(" ").map { it.toInt() }.getNext()
    }

    private fun List<Int>.getNext(): Int = when {
        all { it == 0 } -> 0
        else -> last() + zipWithNext { a, b -> b - a }.getNext()
    }

    override fun partTwo() = inputList.sumOf { line ->
        line.split(" ").map { it.toInt() }.getPrevious()
    }

    private fun List<Int>.getPrevious(): Int = when {
        all { it == 0 } -> 0
        else -> first() - zipWithNext { a, b -> b - a }.getPrevious()
    }

}

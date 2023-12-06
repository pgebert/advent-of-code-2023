package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day06(input: String? = null) : Day(6, "Wait For It", input) {

    override fun partOne(): Long {
        val times = inputList.first().removePrefix("Time:").split(" ").mapNotNull { it.toLongOrNull() }
        val distances = inputList.last().removePrefix("Time:").split(" ").mapNotNull { it.toLongOrNull() }

        return times.zip(distances).map { (time, distance) ->
            countWaysToWin(time, distance)
        }.multiply()
    }

    override fun partTwo(): Long {
        val time = inputList.first().filter { it.isDigit() }.toLong()
        val distance = inputList.last().filter { it.isDigit() }.toLong()

        return countWaysToWin(time, distance)
    }

    private fun countWaysToWin(time: Long, distance: Long) =
        (0..time).count { hold -> (time - hold) * hold > distance }.toLong()

    private fun Iterable<Long>.multiply() = reduce { acc, element -> acc * element }
}

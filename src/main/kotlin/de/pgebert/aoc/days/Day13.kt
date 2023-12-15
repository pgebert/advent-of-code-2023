package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.min

class Day13(input: String? = null) : Day(13, "Point of Incidence", input) {

    override fun partOne() = inputString.sumReflectionsBlockwise()

    override fun partTwo() = inputString.sumReflectionsBlockwise(tolerance = 1)

    private fun String.sumReflectionsBlockwise(tolerance: Int = 0): Int {

        val block = mutableListOf<String>()
        var result = 0

        split("\n").map { it.trimIndent() }.forEach { line ->

            if (line.isEmpty()) {

                if (block.isNotEmpty()) {

                    val horizontal = block.findReflection(tolerance)
                    val vertical = block.transpose().findReflection(tolerance)
                    result += horizontal * 100 + vertical

                    block.removeAll { true }
                }
            } else {
                block.add(line)
            }

        }

        return result
    }

    private fun List<String>.findReflection(tolerance: Int = 0): Int {
        for (i in indices) {

            if (isReflection(i, tolerance)) {
                return i
            }
        }
        return 0
    }

    private fun List<String>.transpose(): List<String> {
        var transposed = mutableListOf<String>()
        for (i in first().indices) {
            var col = ""
            forEach { row ->
                col += row[i]
            }
            transposed.add(col)
        }
        return transposed
    }


    private fun List<String>.isReflection(index: Int, tolerance: Int): Boolean {
        // reflection before index

        val range = min(index, size - index)

        if (range <= 0) return false

        var diff = (1..range).sumOf { i ->
            this[index - i].zip(this[index + i - 1]).count { it.first != it.second }
        }

        return diff == tolerance
    }

}

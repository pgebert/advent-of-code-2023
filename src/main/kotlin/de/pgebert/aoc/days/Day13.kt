package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import de.pgebert.aoc.NOT_IMPLEMENTED
import kotlin.math.min

class Day13(input: String? = null) : Day(13, "Point of Incidence", input) {

    override fun partOne(): Int {

        val block = mutableListOf<String>()
        var result = 0

        inputString.split("\n").map { it.trimIndent() }.forEach { line ->

            if (line.isEmpty()) {

                if (block.isNotEmpty()) {

                    val horizontal = block.findReflection()
                    val vertical = block.transpose().findReflection()
                    result += horizontal * 100 + vertical

                    block.removeAll { true }
                }
            } else {
                block.add(line)
            }

        }

        return result
    }

    fun List<String>.findReflection(): Int {
        for (i in indices) {

            if (isReflection(i)) {
                return i
            }
        }
        return 0
    }

    fun List<String>.isReflection(index: Int): Boolean {
        // reflection before index

        val range = min(index, size - index)

        if (range <= 0) return false

        for (i in 1..range) {
            if (this[index - i] != this[index + i - 1]) return false
        }

        return true
    }


    fun List<String>.transpose(): List<String> {
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

    override fun partTwo() = NOT_IMPLEMENTED
}

package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private typealias Point = Pair<Int, Int>

class Day11(input: String? = null) : Day(11, "Cosmic Expansion", input) {

    private val rowsToDuplicate = inputList.indices.filter { i -> inputList[i].all { it == '.' } }
    private val columnsToDuplicate = inputList.first().indices.filter { i -> inputList.all { it[i] == '.' } }

    override fun partOne() = inputList.findGalaxies().computeDistances(2)
    
    override fun partTwo() = inputList.findGalaxies().computeDistances(1000000)

    private fun List<String>.findGalaxies(): List<Point> {
        val galaxies = mutableListOf<Point>()
        for (row in indices) {
            for (col in this[row].indices) {
                if (this[row][col] == '#') {
                    galaxies.add(Pair(row, col))
                }
            }
        }
        return galaxies
    }

    private fun List<Point>.computeDistances(factor: Int): Long {
        val remaining = ArrayDeque<Point>()
        remaining += this

        var distances = 0L

        while (remaining.isNotEmpty()) {
            val a = remaining.removeFirst()
            remaining.forEach { b ->
                distances += a.distanceTo(b, factor)
            }
        }
        return distances
    }

    private fun Point.distanceTo(other: Point, factor: Int): Int {

        val rowsBetween = min(first, other.first)..max(first, other.first)
        val columnsBetween = min(second, other.second)..max(second, other.second)

        return (abs(first - other.first) + abs(second - other.second)
                + rowsToDuplicate.count { it in rowsBetween } * (factor - 1)
                + columnsToDuplicate.count { it in columnsBetween } * (factor - 1))
    }
}

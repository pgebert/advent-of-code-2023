package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


class Day11(input: String? = null) : Day(11, "Cosmic Expansion", input) {

    override fun partOne(): Long = with(inputList) {
        val (rowsToDuplicate, columnsToDuplicate) = findRowsAndColumnsToDuplicate()
        return findGalaxies().computeDistances(rowsToDuplicate, columnsToDuplicate, 2)
    }

    override fun partTwo(): Long = with(inputList) {
        val (rowsToDuplicate, columnsToDuplicate) = findRowsAndColumnsToDuplicate()
        return findGalaxies().computeDistances(rowsToDuplicate, columnsToDuplicate, 1000000)
    }

    private fun List<String>.findGalaxies(): List<Pair<Int, Int>> {
        val galaxies = mutableListOf<Pair<Int, Int>>()
        for (row in indices) {
            for (col in this[row].indices) {
                if (this[row][col] == '#') {
                    galaxies.add(Pair(row, col))
                }
            }
        }
        return galaxies
    }

    private fun List<Pair<Int, Int>>.computeDistances(
        rowsToDuplicate: List<Int>,
        columnsToDuplicate: List<Int>,
        factor: Int
    ): Long {
        val remaining = ArrayDeque<Pair<Int, Int>>()
        remaining += this

        var distances = 0L

        while (remaining.isNotEmpty()) {
            val a = remaining.removeFirst()
            remaining.forEach { b ->
                distances += a.distanceTo(b, rowsToDuplicate, columnsToDuplicate, factor)
            }
        }
        return distances
    }

    private fun Pair<Int, Int>.distanceTo(
        other: Pair<Int, Int>,
        rowsToDuplicate: List<Int>,
        columnsToDuplicate: List<Int>,
        factor: Int
    ) = (abs(first - other.first)
            + abs(second - other.second)
            + rowsToDuplicate.count { it in min(first, other.first)..max(first, other.first) } * (factor - 1)
            + columnsToDuplicate.count { it in min(second, other.second)..max(second, other.second) } * (factor - 1))

    private fun List<String>.findRowsAndColumnsToDuplicate(): Pair<List<Int>, List<Int>> {
        val rowsToDuplicate = indices.filter { i -> inputList[i].all { it == '.' } }
        val columnsToDuplicate = first().indices.filter { i -> inputList.all { it[i] == '.' } }
        return Pair(rowsToDuplicate, columnsToDuplicate)
    }

}

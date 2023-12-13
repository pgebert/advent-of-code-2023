package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import de.pgebert.aoc.NOT_IMPLEMENTED
import kotlin.math.abs


class Day11(input: String? = null) : Day(11, "Cosmic Expansion", input) {

    override fun partOne(): Int {

        val universum = inputList.map { it.toList() }.expandHorizontal().expandVertical()

        return universum.findGalaxies().computeDistances()

    }

    private fun List<List<Char>>.findGalaxies(): List<Pair<Int, Int>> {
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

    private fun List<Pair<Int, Int>>.computeDistances(): Int {
        val remaining = ArrayDeque<Pair<Int, Int>>()
        remaining += this

        var distances = 0

        while (remaining.isNotEmpty()) {
            val a = remaining.removeFirst()
            remaining.forEach { b ->
                distances += a.distanceTo(b)
            }
        }
        return distances
    }

    private fun Pair<Int, Int>.distanceTo(other: Pair<Int, Int>) =
        abs(first - other.first) + abs(second - other.second)


    override fun partTwo() = NOT_IMPLEMENTED

    fun List<List<Char>>.expandHorizontal() = buildList {
        this@expandHorizontal.forEach { row ->
            add(row)
            if (row.all { it == '.' }) add(row)
        }
    }

    fun List<List<Char>>.expandVertical() = this.transpose().expandHorizontal().transpose()


    fun <T> List<List<T>>.transpose(): List<List<T>> = buildList {
        for (i in this@transpose.first().indices) {
            val col: MutableList<T> = ArrayList()
            this@transpose.forEach { row ->
                col.add(row[i])
            }
            add(col)
        }
    }
}

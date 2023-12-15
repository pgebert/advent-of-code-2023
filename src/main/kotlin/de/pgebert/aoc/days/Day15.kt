package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import de.pgebert.aoc.NOT_IMPLEMENTED

class Day15(input: String? = null) : Day(15, "Lens Library", input) {

    override fun partOne() = inputList.first().split(",").sumOf { part ->
        part.hash()
    }


    fun String.hash() = fold(0) { agg, char -> ((agg + char.code) * 17) % 256 }


    override fun partTwo() = NOT_IMPLEMENTED
}

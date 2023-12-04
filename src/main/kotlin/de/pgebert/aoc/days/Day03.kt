package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.max
import kotlin.math.min

class Day03(input: String? = null) : Day(3, "Gear Ratios", input) {

    private val numberRegex = "\\d+".toRegex()
    private val specialCharacterRegex = "[^\\d.]".toRegex()
    private val gearRegex = "\\*".toRegex()

    override fun partOne() = inputList.mapIndexed { row, line ->

        numberRegex.findAll(line).sumOf { match ->

            val rangeStart = max(match.range.first - 1, 0)
            val rangeEnd = min(match.range.last + 1, inputList.first().length - 1)

            val isSpecialCharacterAdjacent = isSpecialCharacterMatch(row, IntRange(rangeStart, rangeEnd))

            if (isSpecialCharacterAdjacent) match.value.toInt() else 0
        }
    }.sum()


    private fun isSpecialCharacterMatch(rowIndex: Int, range: IntRange): Boolean {
        val previousRow = inputList.getOrNull(rowIndex - 1)?.slice(range)?.contains(specialCharacterRegex)
        val nextRow = inputList.getOrNull(rowIndex + 1)?.slice(range)?.contains(specialCharacterRegex)
        val currentRowLeft = inputList[rowIndex].getOrNull(range.first)?.toString()?.matches(specialCharacterRegex)
        val currentRowRight = inputList[rowIndex].getOrNull(range.last)?.toString()?.matches(specialCharacterRegex)

        return listOf(previousRow, nextRow, currentRowLeft, currentRowRight).contains(true)
    }

    override fun partTwo() = inputList.mapIndexed { row, line ->

        if (0 < row && row < inputList.size - 1) 0

        gearRegex.findAll(line).sumOf { match ->

            val rangeStart = max(match.range.first - 1, 0)
            val rangeEnd = min(match.range.last + 1, inputList.first().length - 1)

            val numbers = findMatchingNumbers(row, IntRange(rangeStart, rangeEnd))

            if (numbers.size == 2) numbers.first() * numbers.last() else 0
        }
    }.sum()


    private fun findMatchingNumbers(rowIndex: Int, range: IntRange): List<Int> {
        val previousRow = numberRegex.findAll(inputList[rowIndex - 1]).filter { it.range.intersect(range).isNotEmpty() }
        val currentRow = numberRegex.findAll(inputList[rowIndex]).filter { it.range.intersect(range).isNotEmpty() }
        val nextRow = numberRegex.findAll(inputList[rowIndex + 1]).filter { it.range.intersect(range).isNotEmpty() }

        return (previousRow + currentRow + nextRow).map { it.value.toInt() }.toList()
    }
}

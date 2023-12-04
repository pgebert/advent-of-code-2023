package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day02(input: String? = null) : Day(2, "Cube Conundrum", input) {

    override fun partOne() =
        inputList.mapNotNull(::getGameIdOfValidInputLine).sum()

    private fun parseGameId(game: String): Int {
        return game.filter { it.isDigit() }.toInt()
    }

    private fun parseSubsets(indicators: String): List<Pair<String, Int>> {
        return indicators.split(";", ",").map {
            it.filter { it.isLetter() } to it.filter { it.isDigit() }.toInt()
        }
    }

    private fun isValidSubset(subset: Pair<String, Int>): Boolean {
        val (color, count) = subset

        return when {
            color == "red" && count <= 12 -> true
            color == "green" && count <= 13 -> true
            color == "blue" && count <= 14 -> true
            else -> false
        }
    }


    private fun getGameIdOfValidInputLine(line: String): Int? {
        val (game, indicators) = line.split(":")

        val gameId = parseGameId(game)

        val subsets = parseSubsets(indicators)

        val valid = subsets.all(::isValidSubset)

        return if (valid) gameId else null
    }


    override fun partTwo() = inputList.mapNotNull { line ->
        val indicators = line.split(":").last()

        val subsets = parseSubsets(indicators)

        var (minRed, minGreen, minBlue) = getMinSetOfCubes(subsets)

        minRed * minGreen * minBlue


    }.sum()

    private fun getMinSetOfCubes(subsets: List<Pair<String, Int>>): Triple<Int, Int, Int> {

        var countMap = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        subsets.forEach { (color, count) ->
            if (count > countMap[color]!!) countMap[color] = count
        }

        return Triple(countMap["red"]!!, countMap["green"]!!, countMap["blue"]!!)
    }
}

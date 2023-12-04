package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day01(input: String? = null) : Day(1, "Trebuchet?!", input) {

    private val numberStringsByValue = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    override fun partOne() =
        inputList
            .map { it.filter { char -> char.isDigit() } }
            .map { "${it.first()}${it.last()}" }
            .sumOf { it.toInt() }


    override fun partTwo() = inputList.map { line ->
        buildString {
            line.forEachIndexed { index, character ->
                if (character.isDigit()) {
                    append(character)
                } else {
                    numberStringsByValue.forEach { (numberString, numberValue) ->
                        if (line.substring(index).startsWith(numberString)) {
                            append(numberValue)
                        }
                    }
                }
            }
        }
    }
        .map { "${it.first()}${it.last()}" }
        .sumOf { it.toInt() }

}

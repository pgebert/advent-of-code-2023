package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.max
import kotlin.math.min

class Day03(input: String? = null) : Day(3, "Gear Ratios", input) {


    val numberRegex = "\\d+".toRegex()
    val characterRegex = "[^\\d.]".toRegex()
    val gearRegex = "\\*".toRegex()


    override fun partOne(): Int {


        var result = 0

        inputList.forEachIndexed { row, line ->

            numberRegex.findAll(line).forEach { match ->

                val range =
                    IntRange(max(match.range.first - 1, 0), min(match.range.last + 1, inputList.first().length - 1))


                if (
                    inputList.getOrNull(row - 1)?.slice(range)?.contains(characterRegex) == true
                    || inputList.getOrNull(row + 1)?.slice(range)?.contains(characterRegex) == true
                    || inputList[row].getOrNull(match.range.first - 1)?.toString()?.matches(characterRegex) == true
                    || inputList[row].getOrNull(match.range.last + 1)?.toString()?.matches(characterRegex) == true
                ) {
                    result += match.value.toInt()
                }


            }
        }

        return result

    }

    override fun partTwo(): Int {
        var result = 0

        inputList.forEachIndexed { index, line ->

            if (0 < index && index < inputList.size - 1) {

                val matches = gearRegex.findAll(line).toList()

                matches.forEach { match ->

                    val range =
                        IntRange(max(match.range.first - 1, 0), min(match.range.last + 1, inputList.first().length - 1))

                    val numbers =
                        numberRegex.findAll(inputList[index - 1]).filter { it.range.intersect(range).isNotEmpty() }
                            .toMutableSet()
                            .plus(
                                numberRegex.findAll(inputList[index])
                                    .filter { it.range.intersect(range).isNotEmpty() })
                            .plus(
                                numberRegex.findAll(inputList[index + 1])
                                    .filter { it.range.intersect(range).isNotEmpty() })
                            .map { it.value.toInt() }


                    if (numbers.size == 2) {
                        result += numbers.first() * numbers.last()
                    }


                }
            }
        }


        return result
    }
}

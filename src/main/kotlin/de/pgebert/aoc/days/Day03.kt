package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.max
import kotlin.math.min

object Day03 : Day(3, "Day3") {


    val numberRegex = "\\d+".toRegex()
    val characterRegex = "[^A-Za-z0-9.]".toRegex()
    val gearRegex = "\\*".toRegex()


    override fun partOne(): Int {


        var result = 0

        println(inputList)

        inputList.forEachIndexed { row, line ->

            val matches = numberRegex.findAll(line).toList()

            matches.forEach { match ->

                val range =
                    IntRange(max(match.range.first - 1, 0), min(match.range.last + 1, inputList.first().length - 1))


                println(inputList)

                if (
                    inputList.getOrNull(row - 1)?.slice(range)?.contains(characterRegex) == true
                    || inputList.getOrNull(row + 1)?.slice(range)?.contains(characterRegex) == true
                    || inputList[row].getOrNull(match.range.first - 1)?.toString()?.matches(characterRegex) == true
                    || inputList[row].getOrNull(match.range.last + 1)?.toString()?.matches(characterRegex) == true
                ) {
                    println("Added ${match.value.toInt()}")
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
                        println("Found gear at ${index}, ${match.range.first} with a=${numbers.first()}, b=${numbers.last()}")
                        result += numbers.first() * numbers.last()
                    }


                }
            }
        }


        return result
    }
}

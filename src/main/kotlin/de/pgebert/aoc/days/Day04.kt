package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.pow

class Day04(input: String? = null) : Day(4, "Scratchcards", input) {

    infix fun Int.`**`(exponent: Int): Int = toDouble().pow(exponent).toInt()

    override fun partOne(): Int {

        val gameSplitter = ".*:(.*)\\|(.*)".toRegex()

        var result = 0

        inputList.forEach { line ->

            val (found, all) = gameSplitter.find(line)!!.destructured

            val wins = found.split(" ").filter { it.isNotBlank() }.toSet()
                .intersect(all.split(" ").filter { it.isNotBlank() }.toSet())

            val worth = when {
                wins.isEmpty() -> 0
                else -> 1 * 2.pow(wins.size - 1)
            }

            result += worth

        }

        return result
    }

    override fun partTwo(): Int {

        val gameSplitter = "Card\\s+(\\d+):(.*)\\|(.*)".toRegex()

        val cardCount = buildMap {
            inputList.indices.forEach {
                put(it + 1, 1)
            }
        }.toMutableMap()


        inputList.forEach { line ->

            val (number, found, all) = gameSplitter.find(line)!!.destructured


            val cardNumber = number.toInt()

            val wins = found.split(" ").filter { it.isNotBlank() }.toSet()
                .intersect(all.split(" ").filter { it.isNotBlank() }.toSet())

            wins.indices.forEach {
                cardCount[cardNumber + it + 1] = cardCount[cardNumber + it + 1]!! + cardCount[cardNumber]!! * 1
            }

        }

        return cardCount.values.sum()

    }
}

private fun Int.pow(n: Int): Int = toDouble().pow(n).toInt()

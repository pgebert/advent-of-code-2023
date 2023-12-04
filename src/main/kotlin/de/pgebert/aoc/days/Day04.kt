package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.pow

class Day04(input: String? = null) : Day(4, "Scratchcards", input) {

    private val scratchcard = ".*:(.*)\\|(.*)".toRegex()

    override fun partOne() = inputList.sumOf { line ->
        scratchcard.find(line)
            ?.destructured
            ?.map { it.split(" ").filter { it.isNotBlank() }.toSet() }
            ?.let { (winning, all) -> winning.intersect(all).powTwoMinusOne() }
            ?: 0
    }

    override fun partTwo(): Int {

        val cardCount = inputList.indices.associateWith { 1 }.toMutableMap()

        inputList.mapIndexedNotNull { card, line ->
            scratchcard.find(line)
                ?.destructured
                ?.map { it.split(" ").filter { it.isNotBlank() }.toSet() }
                ?.let { (winning, all) -> Pair(card, winning.intersect(all)) }
        }.forEach { (card, wins) ->
            wins.indices.forEach { i ->
                cardCount[card + i + 1] = cardCount[card + i + 1]!! + cardCount[card]!!
            }
        }

        return cardCount.values.sum()
    }

    private fun Set<String>.powTwoMinusOne(): Int = if (isNotEmpty()) (2.0).pow(size - 1).toInt() else 0
    private fun <R> MatchResult.Destructured.map(transform: (String) -> R) = toList().map(transform)
}

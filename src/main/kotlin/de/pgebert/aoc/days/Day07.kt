package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day07(input: String? = null) : Day(7, "Camel Cards ", input) {

    private val positionsWithoutJoker = listOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')

    private val positionsWithJoker = listOf('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A')


    override fun partOne() = inputList
        .map { it.parse() }
        .sortedWith(
            compareBy<Pair<String, Int>> { it.first.getType() }
                .thenComparing({ it.first }, { a, b -> a.compareHandTo(b) })
        )
        .mapIndexed { i, (_, bid) -> (i + 1) * bid }
        .sum()

    private fun String.parse() = split(" ").let { it.first() to it.last().toInt() }

    private fun String.getType(): Int {
        val counts = groupingBy { it }.eachCount().values.sortedDescending()
        val first = counts.getOrElse(0) { 0 }
        val second = counts.getOrElse(1) { 0 }

        return when {
            first == 5 -> 6 // Five of a kind
            first == 4 -> 5 // Four of a kind
            first == 3 && second == 2 -> 4 // Full house
            first == 3 -> 3 // Three of a kind
            first == 2 && second == 2 -> 2 // Two pair
            first == 2 -> 1 // One pair
            else -> 0 // High card
        }
    }

    private fun String.compareHandTo(other: String): Int {

        if (isEmpty()) 0

        val thisValue = positionsWithoutJoker.indexOf(first())
        val otherValue = positionsWithoutJoker.indexOf(other.first())

        return thisValue.compareTo(otherValue).takeIf { it != 0 } ?: drop(1).compareHandTo(other.drop(1))
    }


    override fun partTwo() = inputList.map {
        it.parse()
    }
        .sortedWith(
            compareBy<Pair<String, Int>> { it.first.getTypeWithJoker() }.thenComparing(
                { it.first },
                { a, b ->
                    a.compareHandWithJokerTo(b)
                }
            )
        )
        .mapIndexed { index, (_, bid) -> (index + 1) * bid }.sum()


    private fun String.compareHandWithJokerTo(other: String): Int {

        if (isEmpty()) 0

        val thisValue = positionsWithJoker.indexOf(first())
        val otherValue = positionsWithJoker.indexOf(other.first())

        return thisValue.compareTo(otherValue).takeIf { it != 0 } ?: drop(1).compareHandWithJokerTo(other.drop(1))
    }


    private fun String.getTypeWithJoker(): Int {
        val counts = groupingBy { it }.eachCount()
        val first = counts.filterKeys { it != 'J' }.values.sortedDescending().getOrElse(0) { 0 }
        val second = counts.filterKeys { it != 'J' }.values.sortedDescending().getOrElse(1) { 0 }
        val joker = counts.getOrDefault('J', 0)

        return when {
            first + joker == 5 -> 6 // Five of a kind
            first + joker == 4 -> 5 // Four of a kind
            first + joker == 3 && second == 2 -> 4 // Full house
            first + joker == 3 -> 3 // Three of a kind
            first == 2 && second + joker == 2 -> 2 // Two pair
            first + joker == 2 -> 1 // One pair
            else -> 0 // High card
        }
    }
}

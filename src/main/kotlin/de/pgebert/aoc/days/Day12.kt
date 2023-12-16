package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day12(input: String? = null) : Day(12, "Hot Springs", input) {

    override fun partOne() = inputList.sumOf { line ->

        val splitted = line.split(" ")

        val configuration = splitted.first()
        val specification = splitted.last().split(",").map { it.toInt() }

        countPossibleMatches(configuration, specification)
    }

    override fun partTwo() = inputList.sumOf { line ->

        val splitted = line.split(" ")

        val configuration = buildList { repeat(5) { add(splitted.first()) } }.joinToString("?")
        val specification = buildList { repeat(5) { addAll(splitted.last().split(",").map { it.toInt() }) } }

        countPossibleMatches(configuration, specification)
    }


    private fun countPossibleMatches(text: String, numbers: List<Int>): Long {

        val states = buildString {
            append(".")
            numbers.forEach { number ->
                repeat(number) { append("#") }
                append(".")
            }
        }

        var countByState = mutableMapOf(0 to 1L)
        var newCountByState = mutableMapOf<Int, Long>()

        text.forEach { character ->
            countByState.forEach { (state, count) ->
                if (character == '?') {
                    // move to next state
                    if (state + 1 < states.length) {
                        newCountByState[state + 1] = newCountByState.getOrDefault(state + 1, 0) + count
                    }
                    // stay in current state
                    if (states[state] == '.') {
                        newCountByState[state] = newCountByState.getOrDefault(state, 0) + count
                    }
                } else if (character == '.') {
                    // move to next state
                    if (state + 1 < states.length && states[state + 1] == '.') {
                        newCountByState[state + 1] = newCountByState.getOrDefault(state + 1, 0) + count
                    }
                    // stay in current state
                    if (states[state] == '.') {
                        newCountByState[state] = newCountByState.getOrDefault(state, 0) + count
                    }
                } else if (character == '#') {
                    // move to next state
                    if (state + 1 < states.length && states[state + 1] == '#') {
                        newCountByState[state + 1] = newCountByState.getOrDefault(state + 1, 0) + count
                    }
                }
            }
            countByState = newCountByState
            newCountByState = mutableMapOf()
        }

        return (countByState.getOrDefault(states.length - 1, 0) + countByState.getOrDefault(states.length - 2, 0))

    }

}

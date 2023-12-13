package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day12(input: String? = null) : Day(12, "Hot Springs", input) {

    override fun partOne() = inputList.sumOf { line ->

        val (unknown, specification) = line.split(" ")

        unknown.candidates().count { configuration ->
            isValid(configuration, specification)
        }
    }

    fun String.candidates() = sequence {

        val placeholder = "?"
        val replacements = listOf(".", "#")

        val queue = ArrayDeque<String>()
        queue += this@candidates

        while (queue.isNotEmpty()) {
            val next = queue.removeFirst()
            if (next.contains(placeholder)) {
                replacements.forEach { replacement ->
                    queue += next.replaceFirst(placeholder, replacement)
                }
            } else {
                yield(next)
            }

        }


    }

    fun isValid(configuration: String, specification: String): Boolean {
        val pattern = buildString {
            append("^\\.*")
            specification.split(",").forEach {
                append("#{$it}(\\.+|$)")
            }
        }.toRegex()

        return pattern.matches(configuration)
    }

    override fun partTwo() = inputList.sumOf { line ->

        val (unknown, specification) = line.split(" ").unfold()

        unknown.candidates().count { configuration ->
            isValid(configuration, specification)
        }
    }

    fun List<String>.unfold() =
        MutableList(5) { this[0] }.joinToString("?") to MutableList(5) { this[1] }.joinToString("")

}

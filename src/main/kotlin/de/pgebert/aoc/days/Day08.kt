package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import de.pgebert.aoc.utils.lcm

class Day08(input: String? = null) : Day(8, "Haunted Wasteland", input) {


    private val nodeRegex = "(\\S+) = \\((\\S+), (\\S+)\\)".toRegex()

    override fun partOne(): Int {

        val (instructions, nodes) = parseInput()

        var currentNode = "AAA"
        var steps = 0

        while (currentNode != "ZZZ") {

            currentNode = nodes[currentNode]!!.let {
                when (instructions[steps % instructions.length]) {
                    'L' -> it.first
                    else -> it.second
                }
            }
            steps++
        }
        return steps
    }

    override fun partTwo(): Long {

        val (instructions, nodes) = parseInput()

        var startNodes = nodes.keys.filter { it.endsWith("A") }

        return startNodes.map {
            var currentNode = it
            var steps = 0

            while (!currentNode.endsWith("Z")) {

                currentNode = nodes[currentNode]!!.let {
                    when (instructions[steps % instructions.length]) {
                        'L' -> it.first
                        else -> it.second
                    }
                }
                steps++
            }
            steps.toLong()
        }.reduce(::lcm)
    }


    private fun parseInput(): Pair<String, Map<String, Pair<String, String>>> {
        val instructions = inputList.first()

        val nodes = inputList.filter { it.isNotEmpty() }.drop(1).map { line ->
            val (start, left, right) = nodeRegex.find(line)!!.destructured
            start to Pair(left, right)
        }.toMap()

        return Pair(instructions, nodes)
    }
}

package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import de.pgebert.aoc.utils.lcm

class Day08(input: String? = null) : Day(8, "Haunted Wasteland", input) {


    private val nodeRegex = "(\\S+) = \\((\\S+), (\\S+)\\)".toRegex()

    override fun partOne(): Int {

        val instructions = inputList.first()

        val nodes = inputList.filter { it.isNotEmpty() }.drop(1).map { line ->
            val (start, left, right) = nodeRegex.find(line)!!.destructured
            start to Pair(left, right)
        }.toMap()

        var currentNode = "AAA"
        var steps = 0

        while (true) {

            if (currentNode == "ZZZ") break

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

        val instructions = inputList.first()

        val nodes = inputList.filter { it.isNotEmpty() }.drop(1).map { line ->
            val (start, left, right) = nodeRegex.find(line)!!.destructured
            start to Pair(left, right)
        }.toMap()


        var startNodes = nodes.keys.filter { it.endsWith("A") }
        val stepList = mutableListOf<Int>()


        startNodes.forEach {
            var currentNode = it
            var steps = 0

            while (true) {

                if (currentNode.endsWith("Z")) break

                currentNode = nodes[currentNode]!!.let {
                    when (instructions[steps % instructions.length]) {
                        'L' -> it.first
                        else -> it.second
                    }
                }

                steps++

            }
            stepList.add(steps)
        }

        return stepList.map { it.toLong() }.reduce(::lcm).toLong()

    }

}

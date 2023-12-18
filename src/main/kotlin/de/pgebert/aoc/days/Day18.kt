package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.abs

class Day18(input: String? = null) : Day(18, "Lavaduct Lagoo", input) {

    data class Vector(val x: Long, val y: Long) {

        fun plus(other: Vector) = Vector(x + other.x, y + other.y)

        fun multiply(multiplier: Long) = Vector(x * multiplier, y * multiplier)
    }

    override fun partOne(): Long {

        val plan = inputList.map { line ->
            val (direction, steps) = line.split(" ")
            direction.toVector().multiply(steps.toLong())
        }

        return countPointsInside(plan)
    }

    override fun partTwo(): Long {

        val colorRegex = "#(.{5})(.)".toRegex()

        val plan = inputList.map { line ->
            val (steps, direction) = colorRegex.find(line)!!.destructured
            direction.toVector().multiply(steps.toLong(radix = 16))
        }

        return countPointsInside(plan)
    }

    private fun countPointsInside(plan: List<Vector>) =
        pickTheorem(shoelaceFormula(cornerPositions(plan)), perimeter(plan))

    private fun cornerPositions(plan: List<Vector>) =
        plan.scan(Vector(0, 0)) { acc, shiftVector -> acc.plus(shiftVector) }

    // number of coordinates (vertices // points) on the edge of the polygon
    private fun perimeter(plan: List<Vector>) = plan.sumOf { abs(it.x) + abs(it.y) }

    // number of coordinates inside
    private fun shoelaceFormula(corners: List<Vector>) = corners.zipWithNext { a, b ->
        a.x * b.y - a.y * b.x
    }.sum() / 2

    // area (== count('#')) by number of coordinates on perimeter and number of coordinates inside
    private fun pickTheorem(area: Long, perimeter: Long) = area + perimeter / 2 + 1

    private fun String.toVector(): Vector = when (this) {
        "0", "R" -> Vector(1, 0)
        "2", "L" -> Vector(-1, 0)
        "3", "U" -> Vector(0, -1)
        "1", "D" -> Vector(0, 1)
        else -> throw IllegalArgumentException("Invalid direction")
    }
}

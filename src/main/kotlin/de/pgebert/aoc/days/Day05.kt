package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.max
import kotlin.math.min

class Day05(input: String? = null) : Day(5, "If You Give A Seed A Fertilizer", input) {


    private val SEEDS_PREFIX = "seeds:"


    override fun partOne(): Long = with(inputList.iterator()) {
        var value = next().removePrefix(SEEDS_PREFIX).split(" ").mapNotNull { it.toLongOrNull() }.min()

        for (line in this) {

            if (line.isBlank() || line.first().isLetter()) continue

            val (dstStart, srcStart, length) = line.split(" ").map { it.toLong() }

            if (value in srcStart..<srcStart + length) {
                value = dstStart + value - srcStart
                jumpToNextMapping()
            }
        }
        return@with value
    }

    private fun Iterator<String>.jumpToNextMapping() {
        while (hasNext()) {
            val line = next()
            if (line.isNotBlank() && line.first().isLetter()) break
        }
    }


    override fun partTwo(): Long = with(inputList.iterator()) {

        var unprocessed = next().removePrefix(SEEDS_PREFIX).split(" ")
            .mapNotNull { it.toLongOrNull() }
            .windowed(2, 2)
            .map { (a, b) -> a..<a + b }

        var mapped = mutableListOf<LongRange>()

        for (line in this@with) {

            if (line.isBlank()) continue

            if (line.first().isLetter()) {
                unprocessed += mapped
                mapped = mutableListOf()
                continue
            }

            val (dstStart, srcStart, length) = line.split(" ").map { it.toLong() }

            unprocessed = buildList {
                unprocessed.forEach { range ->

                    val iMin = max(range.first, srcStart)
                    val iMax = min(range.last, srcStart + length - 1)
                    val intersection = dstStart + iMin - srcStart..dstStart + iMax - srcStart

                    if (!intersection.isEmpty()) {
                        when {
                            iMin > range.first -> add(range.first..<iMin)
                            iMax < range.last -> add(iMax + 1..<range.last)
                        }
                        mapped.add(intersection)
                    } else {
                        add(range)
                    }

                }
            }
        }

        return (unprocessed + mapped).map { it.first }.min()
    }


}

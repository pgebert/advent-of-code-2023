@file:Suppress("SpellCheckingInspection")

package de.pgebert.aoc

import de.pgebert.aoc.days.*
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory


class AllDaysTest {
    private data class Answer(
        val day: Day,
        val partOne: Any,
        val partTwo: Any
    )

    @TestFactory
    fun answers() = listOf(
        Answer(Day01(), 53974, 52840),
        Answer(Day02(), 2377, 71220),
        Answer(Day03(), 557705, 84266818),
        Answer(Day04(), 33950, 14814534),
        Answer(Day05(), 457535844L, 41222968L),
        Answer(Day06(), 2374848L, 39132886L),
        Answer(Day07(), 250474325, 248909434),
        Answer(Day08(), 21409, 21165830176709),
        Answer(Day09(), 1887980197, 990),
        Answer(Day10(), 6831, NOT_IMPLEMENTED),
        Answer(Day11(), 10033566, 560822911938),
        Answer(Day12(), 8193, 45322533163795),
        Answer(Day13(), 34918, 33054),
        Answer(Day14(), 110274, 90982),
        Answer(Day15(), 512283, 215827),
        Answer(Day16(), 7472, 7716),
        Answer(Day17(), 1099, 1266),
        Answer(Day18(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day19(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day20(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day21(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day22(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day23(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day24(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day25(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
    ).map { (day, expectedPartOne, expectedPartTwo) ->
        DynamicTest.dynamicTest("Day ${day.number} - ${day.title}") {
            print("Testing Day ${day.number} Part 1 - Expecting $expectedPartOne..")
            day.partOne() shouldBe expectedPartOne
            print(" SUCCESS\n")

            print("Testing Day ${day.number} Part 2 - Expecting $expectedPartTwo..")
            day.partTwo() shouldBe expectedPartTwo
            print(" SUCCESS\n")
        }
    }
}

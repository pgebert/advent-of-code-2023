package de.pgebert.aoc

import de.pgebert.aoc.days.Day06
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day06Test {

    private val example = """
        Time:      7  15   30
        Distance:  9  40  200
        """

    @Test
    fun `testing day 6 partOne example`() {

        val day = Day06(input = example)

        day.partOne() shouldBe 288L
    }

    @Test
    fun `testing day 6 partOne`() {

        val day = Day06()

        day.partOne() shouldBe 2374848L
    }


    @Test
    fun `testing day 6 partTwo example`() {

        val day = Day06(input = example)

        day.partTwo() shouldBe 71503L
    }

    @Test
    fun `testing day 6 partTwo`() {

        val day = Day06()

        day.partTwo() shouldBe 39132886L
    }


}

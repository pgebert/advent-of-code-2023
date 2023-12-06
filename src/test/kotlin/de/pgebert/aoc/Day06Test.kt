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
    fun `testing partOne example`() {
        Day06(input = example).partOne() shouldBe 288L
    }

    @Test
    fun `testing partOne`() {
        Day06().partOne() shouldBe 2374848L
    }

    @Test
    fun `testing partTwo example`() {
        Day06(input = example).partTwo() shouldBe 71503L
    }

    @Test
    fun `testing partTwo`() {
        Day06().partTwo() shouldBe 39132886L
    }
}

package de.pgebert.aoc

import de.pgebert.aoc.days.Day09
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day09Test {

    private val example = """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45
        """

    @Test
    fun `testing partOne example`() {
        Day09(input = example).partOne() shouldBe 114
    }

    @Test
    fun `testing partOne`() {
        Day09().partOne() shouldBe 1887980197
    }

    @Test
    fun `testing partTwo example`() {
        Day09(input = example).partTwo() shouldBe 2
    }

    @Test
    fun `testing partTwo`() {
        Day09().partTwo() shouldBe 990
    }

}

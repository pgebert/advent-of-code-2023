package de.pgebert.aoc

import de.pgebert.aoc.days.Day07
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day07Test {

    private val example = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
        """

    @Test
    fun `testing partOne example`() {
        Day07(input = example).partOne() shouldBe 6440
    }

    @Test
    fun `testing partOne`() {
        Day07().partOne() shouldBe 250474325
    }

    @Test
    fun `testing partTwo example`() {
        Day07(input = example).partTwo() shouldBe 5905
    }

    @Test
    fun `testing partTwo`() {
        Day07().partTwo() shouldBe 248909434
    }
}

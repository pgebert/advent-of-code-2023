package de.pgebert.aoc

import de.pgebert.aoc.days.Day01
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day01Test {

    val example = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """

    @Test
    fun `testing partOne example`() {
        Day01(input = example).partOne() shouldBe 142
    }

}

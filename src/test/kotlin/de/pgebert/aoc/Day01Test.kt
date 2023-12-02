package de.pgebert.aoc

import de.pgebert.aoc.days.Day01
import de.pgebert.aoc.utils.shouldBe
import de.pgebert.aoc.utils.withInput
import org.junit.jupiter.api.Test


class Day01Test {

    @Test
    fun `testing day 1 example`() {
        val example = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """

        val day = Day01.withInput(example)

        day.partOne() shouldBe 142
    }
    
}

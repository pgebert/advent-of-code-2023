package de.pgebert.aoc

import de.pgebert.aoc.days.Day03
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day03Test {

    private val example = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """

    @Test
    fun `testing partOne example`() {
        Day03(input = example).partOne() shouldBe 4361
    }

    @Test
    fun `testing partTwo example`() {
        Day03(input = example).partTwo() shouldBe 467835
    }

}

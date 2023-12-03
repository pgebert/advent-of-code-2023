package de.pgebert.aoc

import de.pgebert.aoc.days.Day03
import de.pgebert.aoc.utils.shouldBe
import de.pgebert.aoc.utils.withInput
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
    fun `testing day 3 partOne example`() {


        val day = Day03.withInput(example)

        day.partOne() shouldBe 4361
    }

    @Test
    fun `testing day 3 partTwo example`() {

        val day = Day03.withInput(example)

        day.partTwo() shouldBe 467835
    }

}

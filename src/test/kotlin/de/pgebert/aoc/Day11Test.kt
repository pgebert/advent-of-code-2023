package de.pgebert.aoc

import de.pgebert.aoc.days.Day11
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day11Test {

    private val example = """
        ...#......
        .......#..
        #.........
        ..........
        ......#...
        .#........
        .........#
        ..........
        .......#..
        #...#.....
        """

    @Test
    fun `testing partOne example`() {
        Day11(input = example).partOne() shouldBe 374
    }

    @Test
    fun `testing partOne`() {
        Day11().partOne() shouldBe 10033566
    }

    @Test
    fun `testing partTwo`() {
        Day11().partTwo() shouldBe 560822911938
    }

}

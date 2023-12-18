package de.pgebert.aoc

import de.pgebert.aoc.days.Day18
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day18Test {

    private val example = """
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            D 2 (#d2c081)
            R 2 (#59c680)
            D 2 (#411b91)
            L 5 (#8ceee2)
            U 2 (#caa173)
            L 1 (#1b58a2)
            U 2 (#caa171)
            R 2 (#7807d2)
            U 3 (#a77fa3)
            L 2 (#015232)
            U 2 (#7a21e3)
        """

    @Test
    fun `testing partOne example`() {
        Day18(input = example).partOne() shouldBe 62
    }

    @Test
    fun `testing partOne`() {
        Day18().partOne() shouldBe 34329
    }

    @Test
    fun `testing partTwo example`() {
        Day18(input = example).partTwo() shouldBe 952408144115
    }

    @Test
    fun `testing partTwo`() {
        Day18().partTwo() shouldBe 42617947302920
    }

}

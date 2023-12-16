package de.pgebert.aoc

import de.pgebert.aoc.days.Day14
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day14Test {

    private val example = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """

    @Test
    fun `testing partOne example`() {
        Day14(input = example).partOne() shouldBe 136
    }

    @Test
    fun `testing partOne`() {
        Day14().partOne() shouldBe 110274
    }

    @Test
    fun `testing partTwo example`() {
        Day14(input = example).partTwo() shouldBe 64
    }

    @Test
    fun `testing partTwo`() {
        Day14().partTwo() shouldBe 90982
    }

}

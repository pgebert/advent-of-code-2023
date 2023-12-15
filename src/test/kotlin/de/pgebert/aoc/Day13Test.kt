package de.pgebert.aoc

import de.pgebert.aoc.days.Day13
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day13Test {

    private val example = """
        #.##..##.
        ..#.##.#.
        ##......#
        ##......#
        ..#.##.#.
        ..##..##.
        #.#.##.#.
        
        #...##..#
        #....#..#
        ..##..###
        #####.##.
        #####.##.
        ..##..###
        #....#..#
        """

    @Test
    fun `testing partOne example`() {
        Day13(input = example).partOne() shouldBe 405
    }

    @Test
    fun `testing partOne`() {
        Day13().partOne() shouldBe 34918
    }

    @Test
    fun `testing partTwo example`() {
        Day13(input = example).partTwo() shouldBe 400
    }

    @Test
    fun `testing partTwo`() {
        Day13().partTwo() shouldBe 33054
    }

}

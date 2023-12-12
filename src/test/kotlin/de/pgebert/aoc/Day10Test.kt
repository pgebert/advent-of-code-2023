package de.pgebert.aoc

import de.pgebert.aoc.days.Day10
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day10Test {

    private val example = """
            ..F7.
            .FJ|.
            SJ.L7
            |F--J
            LJ...
        """

    @Test
    fun `testing partOne example`() {
        Day10(input = example).partOne() shouldBe 8
    }

    @Test
    fun `testing partOne`() {
        Day10().partOne() shouldBe 6831
    }

    private val example2 = """
        FF7FSF7F7F7F7F7F---7
        L|LJ||||||||||||F--J
        FL-7LJLJ||||||LJL-77
        F--JF--7||LJLJIF7FJ-
        L---JF-JLJIIIIFJLJJ7
        |F|F-JF---7IIIL7L|7|
        |FFJF7L7F-JF7IIL---7
        7-L-JL7||F7|L7F-7F7|
        L.L7LFJ|||||FJL7||LJ
        L7JLJL-JLJLJL--JLJ.L
        """


    @Test
    fun `testing partTwo example`() {
        Day10(input = example2).partTwo() shouldBe 10
    }

    @Test
    fun `testing partTwo`() {
        Day10().partTwo() shouldBe NOT_IMPLEMENTED

//        350 too high
    }

    @Test
    fun `testing getInitial example`() {
        Day10(input = example).getInitial() shouldBe Day10.Cell(1, 1, 'S', 0)
    }

}

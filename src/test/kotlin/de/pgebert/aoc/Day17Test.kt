package de.pgebert.aoc

import de.pgebert.aoc.days.Day17
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day17Test {

    private val example = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
        """

    @Test
    fun `testing partOne example`() {
        Day17(input = example).partOne() shouldBe 102
    }

    @Test
    fun `testing partOne`() {
        Day17().partOne() shouldBe 1099
    }

    @Test
    fun `testing partTwo example`() {
        Day17(input = example).partTwo() shouldBe 94
    }

    @Test
    fun `testing partTwo`() {
        Day17().partTwo() shouldBe 1266
    }

}

package de.pgebert.aoc

import de.pgebert.aoc.days.Day16
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day16Test {

    private val example = """
            .|...\....
            |.-.\.....
            .....|-...
            ........|.
            ..........
            .........\
            ..../.\\..
            .-.-/..|..
            .|....-|.\
            ..//.|....
        """

    @Test
    fun `testing partOne example`() {
        Day16(input = example).partOne() shouldBe 46
    }

    @Test
    fun `testing partOne`() {
        Day16().partOne() shouldBe 7472
    }

    @Test
    fun `testing partTwo example`() {
        Day16(input = example).partTwo() shouldBe 51
    }

    @Test
    fun `testing partTwo`() {
        Day16().partTwo() shouldBe 7716
    }

}

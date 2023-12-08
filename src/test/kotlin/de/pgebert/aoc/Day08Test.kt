package de.pgebert.aoc

import de.pgebert.aoc.days.Day08
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day08Test {

    private val example = """
        LLR
        
        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
        """

    @Test
    fun `testing partOne example`() {
        Day08(input = example).partOne() shouldBe 6
    }

    @Test
    fun `testing partOne`() {
        Day08().partOne() shouldBe 21409
    }

    @Test
    fun `testing partTwo example`() {
        Day08(input = example).partTwo() shouldBe 6
    }

    @Test
    fun `testing partTwo`() {
        Day08().partTwo() shouldBe 21165830176709
    }

}

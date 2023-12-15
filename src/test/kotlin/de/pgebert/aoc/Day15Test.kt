package de.pgebert.aoc

import de.pgebert.aoc.days.Day15
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day15Test {

    private val example = """
            rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7
        """

    @Test
    fun `testing partOne example`() {
        Day15(input = example).partOne() shouldBe 1320
    }

    @Test
    fun `testing partOne`() {
        Day15().partOne() shouldBe 512283
    }

    @Test
    fun `testing partTwo example`() {
        Day15(input = example).partTwo() shouldBe 145
    }

    @Test
    fun `testing partTwo`() {
        Day15().partTwo() shouldBe 215827
    }

}

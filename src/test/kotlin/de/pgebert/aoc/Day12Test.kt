package de.pgebert.aoc

import de.pgebert.aoc.days.Day12
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day12Test {


    private val example = """
        ???.### 1,1,3
        .??..??...?##. 1,1,3
        ?#?#?#?#?#?#?#? 1,3,1,6
        ????.#...#... 4,1,1
        ????.######..#####. 1,6,5
        ?###???????? 3,2,1
        """

    @Test
    fun `testing partOne example`() {
        Day12(input = example).partOne() shouldBe 21
    }

    @Test
    fun `testing partOne`() {
        Day12().partOne() shouldBe 8193
    }

    @Test
    fun `testing partTwo example`() {
        Day12(input = example).partTwo() shouldBe 525152
    }

    @Test
    fun `testing partTwo`() {
        Day12().partTwo() shouldBe 45322533163795
    }

}

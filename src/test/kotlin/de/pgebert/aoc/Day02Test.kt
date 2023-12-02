package de.pgebert.aoc

import de.pgebert.aoc.days.Day02
import de.pgebert.aoc.utils.shouldBe
import de.pgebert.aoc.utils.withInput
import org.junit.jupiter.api.Test


class Day02Test {

    private val example = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """

    @Test
    fun `testing day 2 partOne example`() {


        val day = Day02.withInput(example)

        day.partOne() shouldBe 8
    }

    @Test
    fun `testing day 2 partTwo example`() {

        val day = Day02.withInput(example)

        day.partTwo() shouldBe 2286
    }

}

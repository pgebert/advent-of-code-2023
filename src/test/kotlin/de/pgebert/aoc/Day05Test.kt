package de.pgebert.aoc

import de.pgebert.aoc.days.Day05
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day05Test {

    private val example = """
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48
        
        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15
        
        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4
        
        water-to-light map:
        88 18 7
        18 25 70
        
        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13
        
        temperature-to-humidity map:
        0 69 1
        1 0 69
        
        humidity-to-location map:
        60 56 37
        56 93 4
        """

    @Test
    fun `testing day 5 partOne example`() {

        val day = Day05(input = example)

        day.partOne() shouldBe 35
    }

    @Test
    fun `testing day 5 partOne`() {

        val day = Day05()

        day.partOne() shouldBe 457535844
    }


    @Test
    fun `testing day 5 partTwo example`() {

        val day = Day05(input = example)

        day.partTwo() shouldBe 46
    }

    @Test
    fun `testing day 5 partTwo`() {

        val day = Day05()

        day.partTwo() shouldBe 41222968
    }


}

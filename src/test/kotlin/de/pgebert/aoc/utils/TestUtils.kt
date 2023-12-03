package de.pgebert.aoc.utils

import de.pgebert.aoc.Day
import io.mockk.every
import io.mockk.spyk
import org.junit.jupiter.api.Assertions

infix fun <T> T.shouldBe(expected: T) = Assertions.assertEquals(expected, this)


fun Day.withInput(input: String): Day = spyk(this, recordPrivateCalls = true).also {
    every { it.getProperty("inputString") } answers { input }
    every { it.getProperty("inputList") } answers { input.toInputList() }
}

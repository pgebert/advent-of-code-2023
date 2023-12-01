package de.pgebert.aoc.utils

import de.pgebert.aoc.Day
import io.mockk.every
import io.mockk.spyk
import org.junit.jupiter.api.Assertions

infix fun <T> T.shouldBe(actual: T) = Assertions.assertEquals(this, actual)


fun Day.withInput(input: String): Day = spyk(this).also {
    every { it.getProperty("inputString") } answers { input }
    every { it.getProperty("inputList") } answers { input.toInputList() }
}

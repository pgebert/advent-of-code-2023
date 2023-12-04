package de.pgebert.aoc.utils

import org.junit.jupiter.api.Assertions

infix fun <T> T.shouldBe(expected: T) = Assertions.assertEquals(expected, this)

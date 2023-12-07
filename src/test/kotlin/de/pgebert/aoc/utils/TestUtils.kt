package de.pgebert.aoc.utils

import org.junit.jupiter.api.Assertions

infix fun <T> T.shouldBe(expected: T) = Assertions.assertEquals(expected, this)
infix fun <T : Comparable<T>> T.shouldBeLessThan(expected: T) =
    Assertions.assertTrue(expected > this, "expected: <$this> to be less than: <$expected>")

infix fun <T : Comparable<T>> T.shouldBeGreaterThan(expected: T) =
    Assertions.assertTrue(expected < this, "expected: <$this> to be greater than: <$expected>")

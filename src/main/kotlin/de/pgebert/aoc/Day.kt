package de.pgebert.aoc

import de.pgebert.aoc.utils.toInputList

abstract class Day(
    val number: Int,
    val title: String,
    private val input: String? = null
) {
    protected val inputString by lazy { input ?: InputReader.readAsString(number) }
    protected val inputList by lazy { input?.toInputList() ?: InputReader.readAsList(number) }


    abstract fun partOne(): Any
    abstract fun partTwo(): Any
}

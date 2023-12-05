package de.pgebert.aoc

import de.pgebert.aoc.utils.toInputList

abstract class Day(
    val number: Int,
    val title: String,
    private val input: String? = null
) {
    // per default takes the file input except a custom input is provided via input argument
    val inputString by lazy { input ?: InputReader.readAsString(number) }
    val inputList by lazy { input?.toInputList() ?: InputReader.readAsList(number) }

    abstract fun partOne(): Any
    abstract fun partTwo(): Any
}

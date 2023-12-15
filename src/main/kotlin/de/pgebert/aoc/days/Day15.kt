package de.pgebert.aoc.days

import de.pgebert.aoc.Day


class Day15(input: String? = null) : Day(15, "Lens Library", input) {

    override fun partOne() = inputList.first().split(",").sumOf { it.hash() }

    private fun String.hash() = fold(0) { agg, char -> ((agg + char.code) * 17) % 256 }


    data class Lens(val label: String, val focal: Int) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Lens) return false

            if (label != other.label) return false

            return true
        }
    }


    override fun partTwo(): Int {
        val boxes = mutableMapOf<Int, MutableList<Lens>>()


        inputList.first().split(",").forEach { instruction ->

            if (instruction.contains('=')) {
                val (label, focal) = instruction.split('=')
                val lens = Lens(label, focal.toInt())
                val boxNumber = label.hash()

                val box = boxes.getOrPut(boxNumber) { mutableListOf() }

                when {
                    lens in box -> box.update(lens)
                    else -> box.add(lens)
                }
            } else {
                val label = instruction.dropLast(1)
                val boxNumber = label.hash()

                val box = boxes.getOrPut(boxNumber) { mutableListOf() }
                val lens = Lens(label, 0)

                box.remove(lens)
            }

        }

        return boxes.map { (boxNumber, box) ->
            box.mapIndexed { index, lens -> (boxNumber + 1) * (index + 1) * lens.focal }.sum()
        }.sum()
    }

    private fun <T> MutableList<T>.update(item: T) {
        val index = indexOf(item)
        removeAt(index)
        add(index, item)
    }
}

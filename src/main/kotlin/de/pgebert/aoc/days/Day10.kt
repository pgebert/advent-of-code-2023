package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day10(input: String? = null) : Day(10, "Pipe Maze", input) {

    data class Cell(
        val x: Int,
        val y: Int,
        val char: Char,
        val weight: Int
    )

    data class Point(val x: Int, val y: Int)


    override fun partOne() = findLoop().maxOf { it.weight }

    override fun partTwo() = findLoop().countEnclosed()


    private fun findLoop(): MutableSet<Cell> {
        val visited = mutableSetOf<Cell>()
        val queue = ArrayDeque<Cell>()
        queue += getInitial()
        while (queue.isNotEmpty()) {
            val cell = queue.removeFirst()
            visited.add(cell)

            val successors = cell.getSuccessors()

            successors.forEach { successor ->
                val visitedCell = visited.firstOrNull { it.x == successor.x && it.y == successor.y }
                if (visitedCell != null) {
                    if (successor.weight < visitedCell.weight) {
                        visited.remove(visitedCell)
                        queue.add(successor)
                    }
                } else {
                    queue.add(successor)
                }
            }
        }
        return visited
    }

    private fun getInitial(): Cell {
        for (x in inputList.indices) {
            for (y in inputList[x].indices) {
                if (inputList[x][y] == 'S') return Cell(x, y, 'S', 0)
            }
        }
        throw Exception("Can not find start")
    }

    private fun Set<Cell>.countEnclosed(): Int {
        var enclosed = 0
        val loop = map { Point(it.x, it.y) }

        for (x in inputList.indices) {
            var inside = false
            for (y in inputList[x].indices) {

                val current = inputList[x][y]
                val next = inputList[x].drop(y + 1).firstOrNull { it != '-' }

                if (Point(x, y) in loop) {
                    if (current == '|' || (current == 'F' && next == 'J') || (current == 'L' && next == '7')) {
                        inside = !inside
                    }
                } else if (inside) {
                    enclosed++
                }
            }
        }
        return enclosed
    }

    private fun Cell.getSuccessors(): List<Cell> {

        val left = inputList[x].getOrNull(y - 1)
        val right = inputList[x].getOrNull(y + 1)
        val top = inputList.getOrNull(x - 1)?.get(y)
        val bottom = inputList.getOrNull(x + 1)?.get(y)

        return buildList {
            if (char == 'S') {
                if (right != null && right in "-J7") add(Cell(x, y + 1, right, weight + 1))
                if (left != null && left in "-LF") add(Cell(x, y - 1, left, weight + 1))
                if (top != null && top in "|F7") add(Cell(x - 1, y, top, weight + 1))
                if (bottom != null && bottom in "|LJ") add(Cell(x + 1, y, bottom, weight + 1))
            } else if (char == '|') {
                if (top != null && top in "|F7") add(Cell(x - 1, y, top, weight + 1))
                if (bottom != null && bottom in "|LJ") add(Cell(x + 1, y, bottom, weight + 1))
            } else if (char == '-') {
                if (right != null && right in "-J7") add(Cell(x, y + 1, right, weight + 1))
                if (left != null && left in "-LF") add(Cell(x, y - 1, left, weight + 1))
            } else if (char == 'L') {
                if (right != null && right in "-J7") add(Cell(x, y + 1, right, weight + 1))
                if (top != null && top in "|F7") add(Cell(x - 1, y, top, weight + 1))
            } else if (char == 'J') {
                if (left != null && left in "-LF") add(Cell(x, y - 1, left, weight + 1))
                if (top != null && top in "|F7") add(Cell(x - 1, y, top, weight + 1))
            } else if (char == '7') {
                if (left != null && left in "-LF") add(Cell(x, y - 1, left, weight + 1))
                if (bottom != null && bottom in "|LJ") add(Cell(x + 1, y, bottom, weight + 1))
            } else if (char == 'F') {
                if (right != null && right in "-J7") add(Cell(x, y + 1, right, weight + 1))
                if (bottom != null && bottom in "|LJ") add(Cell(x + 1, y, bottom, weight + 1))
            }
        }
    }
}

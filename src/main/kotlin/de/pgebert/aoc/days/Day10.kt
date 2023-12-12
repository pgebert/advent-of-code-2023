package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day10(input: String? = null) : Day(10, "Pipe Maze", input) {

    data class Cell(
        val x: Int,
        val y: Int,
        val char: Char,
        val weight: Int
    )

    override fun partOne(): Int {

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

        return visited.maxOf { it.weight }
    }

    fun getInitial(): Cell {
        inputList.forEachIndexed { x, line ->
            line.indexOf('S').takeIf { it != -1 }?.let { y ->
                return Cell(x, y, 'S', 0)
            }
        }
        return Cell(0, 0, 'S', 0)
    }

    fun Cell.getSuccessors(): List<Cell> {

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

    override fun partTwo(): Int {

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

        var enclosed = mutableSetOf<Cell>()

        inputList.forEachIndexed { x, line ->
            line.forEachIndexed { y, char ->


//                if (!visited.any { it.x == x && it.y == y }
//                    && visited.count { it.x == x && it.y < y } % 2 == 1
//                    && visited.count { it.x == x && it.y > y } % 2 == 1
//                    && visited.count { it.y == y && it.x < x } % 2 == 1
//                    && visited.count { it.y == y && it.x > x } % 2 == 1
//                )


                if (!visited.any { it.x == x && it.y == y }
                    && visited.any { it.x > x && it.y == y }
                    && visited.any { it.x < x && it.y == y }
                    && visited.any { it.y > y && it.x == x }
                    && visited.any { it.y < y && it.x == x }
                ) {
                    enclosed.add(Cell(x, y, 'I', 0))
                }

            }
        }


        val stringBuilder = StringBuilder()
        for (x in inputList.indices) {
            for (y in inputList.first().indices) {
                if (visited.any { it.x == x && it.y == y }) {
                    stringBuilder.append("X")
//                    stringBuilder.append(inputList[x][y])
                } else if (enclosed.any { it.x == x && it.y == y }) {
                    stringBuilder.append("I")
                } else {
//                    stringBuilder.append(inputList[x][y])
                    stringBuilder.append(".")
                }
            }
            stringBuilder.append("\n")
        }
        print(stringBuilder)

        return enclosed.size
    }
}

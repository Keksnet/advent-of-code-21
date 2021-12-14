package de.neo.aoc.days.day12

class Cave(private val name: String) {

    private val small = name == name.lowercase()

    private val neighbours = HashSet<Cave>()

    fun getName() = name

    fun isSmall() = small

    fun addNeighbour(neighbour: Cave) {
        neighbours.add(neighbour)
    }

    fun getNeighbours() = neighbours
}
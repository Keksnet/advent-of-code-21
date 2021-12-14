package de.neo.aoc.days

import de.neo.aoc.days.day12.Cave
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Day12 : AbstractDay() {

    override fun useExampleFile() = true

    private val caveSystem = HashMap<String, Cave>()
    private val usedCaves = ArrayList<String>()
    private val paths = ArrayList<Deque<String>>()

    override fun parseInput() {
        val lines = getInput().replace("\r", "").split("\n")

        for(line in lines) {
            val caves = line.split("-")
            caveSystem[caves[0]] = (caveSystem[caves[0]] ?: Cave(caves[0]))
            caveSystem[caves[1]] = (caveSystem[caves[1]] ?: Cave(caves[1]))

            caveSystem[caves[0]]?.addNeighbour(caveSystem[caves[1]]!!)
            caveSystem[caves[1]]?.addNeighbour(caveSystem[caves[0]]!!)
        }
    }

    fun searchWay(cave: Cave, index: Int): Boolean {
        if(cave.getName() in usedCaves) return false
        if(cave.getName() == "end") {
            return true
        }
        var flag = false
        cave.getNeighbours().stream().forEach {
            if(searchWay(cave, index)) {
                if(paths.size < index) {
                    paths.add(ArrayDeque())
                }
                paths[index].push(cave.getName())
                flag = true
            }
        }
        return flag
    }

    override fun part01(): String {

        /* Not solved */

        usedCaves.add("start")
        val i = 0
        caveSystem["start"]?.getNeighbours()?.stream()?.forEach {
            searchWay(it, i)
        }

        return buildString {
            for(caves in paths[0]) {
                append(caveSystem[caves])
            }
            append("-")
        }
    }

    override fun part02(): String {
        return ""
    }
}
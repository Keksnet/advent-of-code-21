package de.neo.aoc.days

import de.neo.aoc.days.day11.Octopus

class Day11 : AbstractDay() {

    override fun useExampleFile() = true

    private val field: ArrayList<ArrayList<Octopus>> = ArrayList()
    private var flashes = 0

    override fun parseInput() {
        val lines = getInput().replace("\r", "").split("\n")
        for(i in lines.indices) {
            val line = lines[i]
            field.add(ArrayList())
            for(j in line.indices) {
                val char = line[j]
                field[i].add(Octopus(char.toString().toInt()))
            }
        }
    }

    fun simulateTick(i: Int, j: Int, octo: Octopus, origin: Octopus) {
        if(octo.tick(!(octo == origin))) {
            flashes++
            if(i != 0 /*&& field[i - 1][j] != origin*/) {
                simulateTick(i - 1, j, field[i - 1][j], octo)
            }
            if(j != 0 /*&& field[i][j - 1] != origin*/) {
                simulateTick(i, j - 1, field[i][j - 1], octo)
            }
            if(j != field[i].size - 1 /*&& field[i][j + 1] != origin*/) {
                simulateTick(i, j + 1, field[i][j + 1], octo)
            }
            if(i != field.size - 1 /*&& field[i + 1][j] != origin*/) {
                simulateTick(i + 1, j, field[i + 1][j], octo)
            }

            if(i != 0 && j != 0 /*&& field[i - 1][j - 1] != origin*/) {
                simulateTick(i - 1, j - 1, field[i - 1][j - 1], octo)
            }
            if(i != field.size - 1 && j != field[i].size - 1 /*&& field[i + 1][j + 1] != origin*/) {
                simulateTick(i + 1, j + 1, field[i + 1][j + 1], octo)
            }
            if(i != 0 && j != field[i].size - 1 /*&& field[i - 1][j + 1] != origin*/) {
                simulateTick(i - 1, j + 1, field[i - 1][j + 1], octo)
            }
            if(i != field.size - 1 && j != 0 /*&& field[i + 1][j - 1] != origin*/) {
                simulateTick(i + 1, j - 1, field[i + 1][j - 1], octo)
            }
        }
    }

    fun simulate(times: Int) {
        for(k in 0 until times) {
            if(k == 0) {
                dump()
            }
            for(i in field.indices) {
                for(j in field[i].indices) {
                    field[i][j].init = true
                    simulateTick(i, j, field[i][j], field[i][j])
                    field[i][j].init = false
                }
            }
            if(k == 0) {
                dump()
            }
        }
    }

    fun dump() {
        for(line in field) {
            for(char in line) {
                print(char.getEnergy())
            }
            println()
        }
        println("\n")
    }

    override fun part01(): String {
        simulate(100)
        return flashes.toString()
    }

    override fun part02(): String {
        return ""
    }
}
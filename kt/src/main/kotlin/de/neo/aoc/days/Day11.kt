package de.neo.aoc.days

import de.neo.aoc.days.day11.Octopus

class Day11 : AbstractDay() {

    override fun useExampleFile() = false

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

    fun simulateTick(i: Int, j: Int, octo: Octopus) {
        if(octo.tick()) {
            flashes++
            if(i != 0 /*&& field[i - 1][j] != origin*/) {
                simulateTick(i - 1, j, field[i - 1][j])
            }
            if(j != 0 /*&& field[i][j - 1] != origin*/) {
                simulateTick(i, j - 1, field[i][j - 1])
            }
            if(j != field[i].size - 1 /*&& field[i][j + 1] != origin*/) {
                simulateTick(i, j + 1, field[i][j + 1])
            }
            if(i != field.size - 1 /*&& field[i + 1][j] != origin*/) {
                simulateTick(i + 1, j, field[i + 1][j])
            }

            if(i != 0 && j != 0 /*&& field[i - 1][j - 1] != origin*/) {
                simulateTick(i - 1, j - 1, field[i - 1][j - 1])
            }
            if(i != field.size - 1 && j != field[i].size - 1 /*&& field[i + 1][j + 1] != origin*/) {
                simulateTick(i + 1, j + 1, field[i + 1][j + 1])
            }
            if(i != 0 && j != field[i].size - 1 /*&& field[i - 1][j + 1] != origin*/) {
                simulateTick(i - 1, j + 1, field[i - 1][j + 1])
            }
            if(i != field.size - 1 && j != 0 /*&& field[i + 1][j - 1] != origin*/) {
                simulateTick(i + 1, j - 1, field[i + 1][j - 1])
            }
        }
    }

    fun simulate(times: Int) {
        for(k in 0 until times) {
            for(i in field.indices) {
                for(j in field[i].indices) {
                    simulateTick(i, j, field[i][j])
                }
            }
            for(line in field) {
                for(octopus in line) {
                    octopus.flashed = false
                }
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
        var i = 100
        while(true) {
            simulate(1)
            var allFlashed = true
            for(line in field) {
                for(octopus in line) {
                    if(octopus.getEnergy() != 0) {
                        allFlashed = false
                        break
                    }
                }
            }
            if(allFlashed) {
                break
            }
            i++
        }
        i++
        return "Flashed after $i times"
    }
}
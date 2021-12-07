package de.neo.aoc.days

class Day07 : AbstractDay() {

    override fun useExampleFile() = false

    private val crabs = ArrayList<Int>()

    override fun parseInput() {
        val input = getInput().split(",")

        for(i in input) {
            crabs.add(i.toInt())
        }
    }

    fun sum(): Int {
        var sum = 0
        for(i in crabs) {
            sum += i
        }
        return sum
    }

    override fun part01(): String {
        var lowestFuel = 10000000
        for(j in 1 until 1000) {
            var fuel = 0
            for(i in crabs) {
                if(i < j) {
                    fuel += j - i
                }else {
                    fuel += i - j
                }
            }
            println("Testing $j @ $fuel")
            if(fuel < lowestFuel) {
                lowestFuel = fuel
            }
        }

        return lowestFuel.toString()
    }

    override fun part02(): String {
        var lowestFuel = 1000000000
        for(j in 1 until 1000) {
            var fuel = 0
            for(i in crabs) {
                if(i < j) {
                    fuel += fuelCosts(i, j)
                }else {
                    fuel += fuelCosts(j, i)
                }
            }
            println("Testing $j @ $fuel")
            if(fuel < lowestFuel) {
                lowestFuel = fuel
            }
        }

        return lowestFuel.toString()
    }

    fun fuelCosts(start: Int, end: Int): Int {
        var fuel = 0
        for(i in start until end) {
            fuel += i - start + 1
        }
        return fuel
    }
}
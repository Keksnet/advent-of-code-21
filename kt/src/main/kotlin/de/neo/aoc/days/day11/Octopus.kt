package de.neo.aoc.days.day11

class Octopus(private var energy: Int) {

    var flashed = false

    fun tick(): Boolean {
        if(flashed) return false
        energy++
        if(energy > 9) {
            energy = 0
            flashed = true
            return true
        }
        return false
    }

    fun getEnergy(): Int {
        return energy
    }

}
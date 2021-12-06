package de.neo.aoc.days.day06

class LaternFish(private var birthCycle: Int = 8) {

    fun tick(): Boolean {
        if(birthCycle == 0) {
            birthCycle = 6
            return true
        }
        birthCycle--
        return false
    }
}
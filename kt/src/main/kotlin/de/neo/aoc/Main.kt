package de.neo.aoc

import de.neo.aoc.days.Day04

class Main {

    private val days = DayManager()

    init {
        Day04()
    }

    fun launch(day: String) {
        days[day].exec()
    }

}

fun main() {
    Main().launch("Day04")
}
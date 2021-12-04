package de.neo.aoc

import de.neo.aoc.days.Day04
import de.neo.aoc.days.Day05

class Main {

    private val days = DayManager()

    init {
        Day04()
        Day05()
    }

    fun launch(day: String) {
        days[day].exec()
    }

}

fun main() {
    Main().launch("Day05")
}
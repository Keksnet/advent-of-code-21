package de.neo.aoc

import de.neo.aoc.days.*

class Main {

    private val days = DayManager()

    init {
        Day04()
        Day05()
        Day06()
        Day07()
        Day08()
    }

    fun launch(day: String) {
        days[day].exec()
    }

}

fun main() {
    Main().launch("Day08")
}
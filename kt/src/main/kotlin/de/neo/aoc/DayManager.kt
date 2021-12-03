package de.neo.aoc

import de.neo.aoc.days.AbstractDay
import de.neo.aoc.days.IDay

class DayManager {

    companion object {
        private lateinit var instance: DayManager

        fun getInstance() = instance
    }

    init {
        instance = this
    }

    private val days: HashMap<String, IDay> = HashMap()

    fun register(day: IDay) {
        days[day.getDayName()] = day
        println(day.getDayName())
    }

    operator fun get(name: String) = days[name] ?: object : AbstractDay() {
        override fun part01() = ""

        override fun part02() = ""

        override fun exec() {
            println("Unknown day: $name")
        }

    }
}
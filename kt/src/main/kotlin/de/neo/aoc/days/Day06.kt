package de.neo.aoc.days

import de.neo.aoc.days.day06.LaternFish
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class Day06 : AbstractDay() {

    override fun useExampleFile() = false

    private var fishes01 = HashSet<LaternFish>()
    private var fishes02 = HashSet<LaternFish>()

    override fun parseInput() {
        val fishs = getInput().split(",")

        for(i in fishs) {
            fishes01.add(LaternFish(i.toInt()))
            fishes02.add(LaternFish(i.toInt()))
        }
    }

    override fun part01(): String {
        simulate(80, fishes01)

        return fishes01.size.toString()
    }

    override fun part02(): String {
	// Did not solve
        simulate(256, fishes02)

        return fishes02.size.toString()
    }

    fun simulate(days: Int, initialFishes: HashSet<LaternFish>) {
        for(i in 0 until days) {
            println("Day: $i @ ${initialFishes.size}")
            val toAdd = HashSet<LaternFish>()
            initialFishes.forEach {
                if(it.tick()) {
                    toAdd.add(LaternFish())
                }
            }
            initialFishes.addAll(toAdd)
        }
    }
}

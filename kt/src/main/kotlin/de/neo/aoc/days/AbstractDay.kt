package de.neo.aoc.days

import de.neo.aoc.DayManager
import java.nio.file.Files
import java.nio.file.Path

abstract class AbstractDay : IDay {

    private var input: String? = null

    init {
        DayManager.getInstance().register(this)
    }

    override fun getInput() = input ?: ""

    override fun getDayName(): String = javaClass.simpleName

    override fun exec() {
        input = if(useExampleFile()) {
            Files.readString(Path.of("${getDayName().lowercase()}.txt.t"))
        }else {
            Files.readString(Path.of("${getDayName().lowercase()}.txt"))
        }
        parseInput()
        println("Part 1: ${part01()}")
        println("Part 2: ${part02()}")
    }

}
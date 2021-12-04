package de.neo.aoc.days

interface IDay {

    fun useExampleFile(): Boolean

    fun parseInput()

    fun part01(): String

    fun part02(): String

    fun getInput(): String

    fun getDayName(): String

    fun exec()

}
package de.neo.aoc.days.day10

class SubmarineSyntaxException(message: String, val wrongChar: Char) : Exception(message) {
}
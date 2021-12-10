package de.neo.aoc.days

import de.neo.aoc.days.day10.SubmarineSyntaxException
import java.util.*
import java.util.logging.Logger

class Day10 : AbstractDay() {

    override fun useExampleFile() = false

    private val lines: ArrayList<String> = ArrayList()
    private val code: ArrayList<String> = ArrayList()
    private val expected: Deque<Char> = ArrayDeque()
    private val logger = Logger.getLogger("Day10")

    override fun parseInput() {
        lines.addAll(getInput().replace("\r", "").split("\n"))
        code.addAll(lines)
    }

    override fun part01(): String {
        var errors = ""
        for(i in lines.indices) {
            val line = lines[i]
            expected.clear()
            try {
                for(j in line.indices) {
                    val char = line[j]
                    when(char) {
                        expected.peek() -> {
                            expected.pop()
                        }

                        '(' -> {
                            expected.push(')')
                        }

                        '[' -> {
                            expected.push(']')
                        }

                        '{' -> {
                            expected.push('}')
                        }

                        '<' -> {
                            expected.push('>')
                        }

                        else -> {
                            throw SubmarineSyntaxException("SyntaxError on line: ${i + 1} at position ${j + 1}: " +
                                    "Expected ${expected.pop()}, but got $char instead", char)
                        }
                    }
                }
            }catch (e: SubmarineSyntaxException) {
                logger.warning(e.message)
                errors += e.wrongChar
                code.remove(line)
            }
        }

        var points = 0
        println(errors)
        for(error in errors) {
            points += when(error) {
                ')' -> 3
                ']' -> 57
                '}' -> 1197
                '>' -> 25137
                else -> throw IllegalArgumentException("Unknown scoring for $error")
            }
        }
        return points.toString()
    }

    override fun part02(): String {
        val appended: HashMap<Int, StringBuilder> = HashMap()
        for(i in code.indices) {
            val line = code[i]
            expected.clear()
            for(j in line.indices) {
                val char = line[j]
                when(char) {
                    expected.peek() -> expected.pop()
                    '(' -> expected.push(')')
                    '[' -> expected.push(']')
                    '{' -> expected.push('}')
                    '<' -> expected.push('>')
                    else -> throw SubmarineSyntaxException("SyntaxError on line: ${i + 1} at position ${j + 1}: " +
                            "Expected ${expected.pop()}, but got $char instead", char)
                }
            }
            for(j in expected.indices) {
                appended[i] = (appended[i] ?: StringBuilder()).append(expected.pop())
            }
        }

        val points: HashMap<Int, Long> = HashMap()
        for(i in code.indices) {
            val line = appended[i].toString().trim()
            for(char in line) {
                points[i] = (points[i] ?: 0L) * 5L
                points[i] = (points[i] ?: 0L) + when(char) {
                    ')' -> 1L
                    ']' -> 2L
                    '}' -> 3L
                    '>' -> 4L
                    else -> throw IllegalArgumentException("Unknown scoring for $char")
                }
            }
            println("line: $line | score: ${points[i]}")
        }


        val score = points.values
        val index = (score.size / 2) + 0.5
        return score.sorted()[index.toInt()].toString()
    }
}
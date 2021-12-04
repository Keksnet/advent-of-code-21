package de.neo.aoc.days

import de.neo.aoc.days.day04.Board

class Day04 : AbstractDay() {

    private var lines: List<String> = ArrayList()
    private var drawn: List<String> = ArrayList()
    private val boards: ArrayList<Board> = ArrayList()

    override fun useExampleFile() = false

    override fun parseInput() {
        lines = getInput().split("\n")
        drawn = lines[0].split(",")

        var prevEmpty = false
        var rowCount = 0
        var rows = ""
        for(i in lines) {
            if(i.isBlank()) {
                prevEmpty = true
                continue
            }
            if(prevEmpty) {
                prevEmpty = false
                rowCount++
                rows += "$i\n"
                continue
            }
            if(rowCount > 0) {
                rowCount++
                rows += "$i\n"
            }
            if(rowCount == 5) {
                println("Saved!")
                val board = Board(rows.trim())
                boards.add(board)
                board.printField()
                rowCount = 0
                rows = ""
            }
        }
    }

    override fun part01(): String {
        var lastDrawn = 0
        var winner: Board? = null
        mainLoop@ for(i in drawn) {
            lastDrawn = i.toInt()
            for(j in boards) {
                val field = j.findField(i)
                if(field.coordinates == "-1;-1") continue
                field.marked = true
                j.updateField(field)
                if(j.checkBingo()) {
                    println("FOUND BINGO!")
                    winner = j
                    break@mainLoop
                }
            }
        }

        return buildString {
            val unmarked = winner!!.calculateUnmarked()
            append("Found Bingo: $unmarked*$lastDrawn=${unmarked * lastDrawn}")
        }
    }

    override fun part02(): String {
        var lastDrawn = 0
        var winner: Board? = null
        mainLoop@ for(i in drawn) {
            lastDrawn = i.toInt()
            for(j in boards) {
                val field = j.findField(i)
                if(field.coordinates == "-1;-1") continue
                field.marked = true
                j.updateField(field)
                if(j.checkBingo()) {
                    var allBingo = true
                    for(k in boards) {
                        if(!k.checkBingo()) {
                            allBingo = false
                        }
                    }
                    if(allBingo) {
                        println("Found last!")
                        winner = j
                        break@mainLoop
                    }
                }
            }
        }

        return buildString {
            val unmarked = winner!!.calculateUnmarked()
            append("Found Bingo: $unmarked*$lastDrawn=${unmarked * lastDrawn}")
        }
    }

}
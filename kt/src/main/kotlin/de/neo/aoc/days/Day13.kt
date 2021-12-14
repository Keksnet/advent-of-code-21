package de.neo.aoc.days

class Day13 : AbstractDay() {

    override fun useExampleFile() = false

    private val dots = ArrayList<String>()
    private val instructions = ArrayList<String>()

    override fun parseInput() {
        val parts = getInput().replace("\r", "").split("\n\n")
        val dotList = parts[0].split("\n")
        instructions.addAll(parts[1].split("\n"))

        for(dot in dotList) {
            dots.add(dot)
        }
    }

    private fun parseInstruction(instruction: String): Pair<String, Int> {
        var inst = instruction
        inst = inst.replace("fold along ", "")
        val parts = inst.split("=")
        return Pair(parts[0], parts[1].toInt())
    }

    private fun fold(instruction: Pair<String, Int>, paper: ArrayList<String>): ArrayList<String> {
        val dotsCp = ArrayList(paper)
        val toRemove = ArrayList<String>()
        val toAdd = ArrayList<String>()
        if(instruction.first == "x") {
            dotsCp.forEach {
                val coords = it.split(",")
                val x = coords[0].toInt()
                if(x > instruction.second) {
                    val len = x - instruction.second
                    toRemove.add(it)
                    val newCoords = "${instruction.second - len},${coords[1]}"
                    if(!dotsCp.contains(newCoords) && !toAdd.contains(newCoords)) {
                        toAdd.add(newCoords)
                    }
                }
            }
        }else if(instruction.first == "y") {
            dotsCp.forEach {
                val coords = it.split(",")
                val y = coords[1].toInt()
                if(y > instruction.second) {
                    val len = y - instruction.second
                    toRemove.add(it)
                    val newCoords = "${coords[0]},${instruction.second - len}"
                    if(!dotsCp.contains(newCoords) && !toAdd.contains(newCoords)) {
                        toAdd.add(newCoords)
                    }
                }
            }
        }
        dotsCp.removeAll(toRemove)
        dotsCp.addAll(toAdd)
        return dotsCp
    }

    override fun part01(): String {
        val instruction = parseInstruction(instructions[0])
        val folded = fold(instruction, dots)

        return folded.size.toString()
    }

    private fun printField(field: ArrayList<String>): String {
        var highestX = 0
        var highestY = 0

        field.forEach {
            val coords = it.split(",")
            if(coords[0].toInt() > highestX) {
                highestX = coords[0].toInt()
                //println("new highest x: $coords")
            }
            if(coords[1].toInt() > highestY) {
                highestY = coords[1].toInt()
                //println("new highest y: $coords")
            }
        }

        return buildString {
            append("\n")
            for(y in 0..highestY) {
                for(x in 0..highestX) {
                    if(field.contains("$x,$y")) append("#")
                    else append(" ")
                }
                append("\n")
            }
        }
    }

    override fun part02(): String {
        var folded = dots
        for(inst in instructions) {
            val instruction = parseInstruction(inst)
            folded = fold(instruction, folded)
        }

        return printField(folded)
    }
}
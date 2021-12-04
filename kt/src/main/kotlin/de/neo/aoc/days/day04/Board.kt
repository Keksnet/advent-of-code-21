package de.neo.aoc.days.day04

class Board(private val input: String) {

    class Field(val coordinates: String, val value: String, var marked: Boolean)

    private val coordinates: HashMap<String, Field> = HashMap()

    init {
        println(input)
        val lines = input.split("\n")
        for(i in lines.indices) {
            val line = lines[i].split(" ")
            var offset = 0
            for(j in line.indices) {
                if(line[j].trim().isBlank()) {
                    offset++
                    continue
                }
                coordinates["${j - offset};$i"] = Field("${j - offset};$i", line[j].trim(), false)
            }
        }
    }

    fun getField(coords: String) = coordinates[coords] ?: Field(coords, "UNKNOWN FIELD", false)

    fun findField(value: String): Field {
        for(i in coordinates.values) {
            if(i.value == value) {
                return i
            }
        }
        return Field("-1;-1", "UNKNOWN FIELD", false)
    }

    fun updateField(field: Field) {
        coordinates[field.coordinates] = field
    }

    fun calculateUnmarked(): Int {
        var unmarked = 0
        for(i in 0..4) {
            for(j in 0..4) {
                val field = getField("$j;$i")
                if(!field.marked) {
                    unmarked += field.value.toInt()
                }
            }
        }
        return unmarked
    }

    fun checkBingo(): Boolean {
        for(i in 0..4) {
            var allMarked = true
            for(j in 0..4) {
                if(!getField("$j;$i").marked) {
                    allMarked = false
                }
            }
            if(allMarked) {
                return true
            }
        }
        for(j in 0..4) {
            var allMarked = true
            for(i in 0..4) {
                if(!getField("$j;$i").marked) {
                    allMarked = false
                }
            }
            if(allMarked) {
                return true
            }
        }
        return false
    }

    fun printField() {
        for(i in 0..4) {
            for(j in 0..4) {
                val f = getField("$j;$i")
                print("${f.value} ")
            }
            println()
        }
        println("\n")
    }
}
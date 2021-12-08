package de.neo.aoc.days

class Day08 : AbstractDay() {

    override fun useExampleFile() = false

    private val input: ArrayList<ArrayList<String>> = ArrayList()
    private val output: ArrayList<ArrayList<String>> = ArrayList()

    override fun parseInput() {
        val lines = getInput().replace("\r", "").split("\n")

        for(line in lines) {
            val inOut = line.replace(" | ", ";").split(";")
            input.add(ArrayList(inOut[0].split(" ")))
            output.add(ArrayList(inOut[1].split(" ")))
        }
    }

    override fun part01(): String {
        var times1 = 0
        var times4 = 0
        var times7 = 0
        var times8 = 0
        for(i in output) {
            for(j in i) {
                if(j.length == 2) {
                    times1++
                }else if(j.length == 4) {
                    times4++
                }else if(j.length == 3) {
                    times7++
                }else if(j.length == 7) {
                    times8++
                }
            }
        }

        return "1: $times1 | 4: $times4 | 7: $times7 | 8: $times8 \n ${times1 + times4 + times7 + times8}"
    }

    override fun part02(): String {
        var result = ""
        var sol = 0
        for(i in input.indices) {
            val inVal = input[i]
            val outVal = output[i]

            // Finds the representation for one
            var oneRep = inVal.stream().filter {
                return@filter it.length == 2
            }.findFirst()
            val oneString = if (oneRep.isPresent) oneRep.get() else {
                oneRep = outVal.stream().filter {
                    return@filter it.length == 2
                }.findFirst()
                if (oneRep.isPresent) oneRep.get() else ""
            }

            // Finds the representation for four
            var fourRep = inVal.stream().filter {
                return@filter it.length == 4
            }.findFirst()
            val fourString = if (fourRep.isPresent) fourRep.get() else {
                fourRep = outVal.stream().filter {
                    return@filter it.length == 4
                }.findFirst()
                if (fourRep.isPresent) fourRep.get() else ""
            }

            // finds the representation for six
            var sixRep = inVal.stream().filter {
                return@filter it.length == 6 && run {
                    var flag = true
                    for (j in oneString) {
                        if (!it.contains(j)) {
                            flag = false
                        }
                    }
                    !flag
                }
            }.findFirst()
            val sixString = if(sixRep.isPresent) sixRep.get() else {
                sixRep = outVal.stream().filter {
                    return@filter it.length == 6 && run {
                        var flag = true
                        for (j in oneString) {
                            if (!it.contains(j)) {
                                flag = false
                            }
                        }
                        !flag
                    }
                }.findFirst()
                if(sixRep.isPresent) sixRep.get() else ""
            }

            // finds the representation for seven
            var sevenRep = inVal.stream().filter {
                return@filter it.length == 3
            }.findFirst()
            val sevenString = if (sevenRep.isPresent) sevenRep.get() else {
                sevenRep = outVal.stream().filter {
                    return@filter it.length == 3
                }.findFirst()
                if (sevenRep.isPresent) sevenRep.get() else ""
            }

            // finds the representation for eight
            val eightString = "abcdefg"

            // finds the representation for six
            var nineRep = inVal.stream().filter {
                return@filter it.length == 6 && run {
                    var flag = true
                    for (j in fourString) {
                        if (!it.contains(j)) {
                            flag = false
                        }
                    }
                    flag
                }
            }.findFirst()
            val nineString = if(nineRep.isPresent) nineRep.get() else {
                nineRep = outVal.stream().filter {
                    return@filter it.length == 6 && run {
                        var flag = true
                        for (j in fourString) {
                            if (!it.contains(j)) {
                                flag = false
                            }
                        }
                        flag
                    }
                }.findFirst()
                if(nineRep.isPresent) nineRep.get() else ""
            }

            // finds the representation for zero
            var zeroRep = inVal.stream().filter {
                return@filter it.length == 6 && run {
                    var flag = true
                    var diff = 0
                    for (j in fourString) {
                        if (!it.contains(j)) {
                            diff++
                        }
                    }
                    for(j in oneString) {
                        if(!it.contains(j)) {
                            diff++
                        }
                    }
                    if(diff == 1) {
                        flag = false
                    }
                    !flag
                }
            }.findFirst()
            val zeroString = if(zeroRep.isPresent) zeroRep.get() else {
                zeroRep = outVal.stream().filter {
                    return@filter it.length == 6 && run {
                        var flag = true
                        var diff = 0
                        for (j in fourString) {
                            if (!it.contains(j)) {
                                diff++
                            }
                        }
                        for(j in oneString) {
                            if(!it.contains(j)) {
                                diff++
                            }
                        }
                        if(diff == 1) {
                            flag = false
                        }
                        !flag
                    }
                }.findFirst()
                if(zeroRep.isPresent) zeroRep.get() else ""
            }

            // Finds the representation for two
            var twoRep = inVal.stream().filter {
                return@filter it.length == 5 && run {
                    var flag = true
                    var differences = 0
                    for(j in sixString) {
                        if(!it.contains(j)) {
                            differences++
                        }
                    }
                    if(differences != 2) {
                        flag = false
                    }
                    var allRight = false
                    for(j in oneString) {
                        if(it.contains(j)) {
                            allRight = true
                        }
                        if(!allRight) {
                            break
                        }
                    }
                    if(allRight) {
                        flag = false
                    }
                    flag
                }
            }.findFirst()
            val twoString = if (twoRep.isPresent) twoRep.get() else {
                twoRep = outVal.stream().filter {
                    return@filter it.length == 5 && run {
                        var flag = true
                        var differences = 0
                        for(j in sixString) {
                            if(!it.contains(j)) {
                                differences++
                            }
                        }
                        if(differences != 2) {
                            flag = false
                        }
                        var allRight = false
                        for(j in oneString) {
                            if(it.contains(j)) {
                                allRight = true
                            }
                            if(!allRight) {
                                break
                            }
                        }
                        if(allRight) {
                            flag = false
                        }
                        flag
                    }
                }.findFirst()
                if (twoRep.isPresent) twoRep.get() else ""
            }

            val upper = run {
                var notAvailable = ""
                for (j in sevenString) {
                    if (!oneString.contains(j)) {
                        notAvailable += j
                    }
                }
                notAvailable
            }

            val rightTop = run {
                var notAvailable = ""
                for (j in oneString) {
                    if (!sixString.contains(j)) {
                        notAvailable += j
                    }
                }
                notAvailable
            }

            val middle = run {
                var notAvailable = ""
                for(j in eightString) {
                    if(!zeroString.contains(j)) {
                        notAvailable += j
                    }
                }
                println("eS: $eightString | zS: $zeroString")
                notAvailable
            }

            val leftBottom = run {
                var notAvailable = ""
                for(j in eightString) {
                    if(!nineString.contains(j)) {
                        notAvailable += j
                    }
                }
                notAvailable
            }

            val rightBottom = run {
                var notAvailable = ""
                for(j in eightString) {
                    if(zeroString == "" || zeroString.contains(j)) {
                        if(oneString == "" || oneString.contains(j)) {
                            if(fourString == "" || fourString.contains(j)) {
                                if(sixString == "" || sixString.contains(j)) {
                                    if(sevenString == "" || sevenString.contains(j)) {
                                        if(eightString == "" || eightString.contains(j)) {
                                            if(nineString == "" || nineString.contains(j)) {
                                                notAvailable += j
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                notAvailable
            }

            val leftTop = run {
                val temp = upper + rightTop + middle + leftBottom + rightBottom + run {
                    var bottom = ""
                    for(j in zeroString) {
                        if(upper.contains(j) || leftBottom.contains(j)) continue
                        if(!fourString.contains(j)) {
                            bottom = j.toString()
                        }
                    }
                    bottom
                }
                println(temp)
                var notAvailable = ""
                for(j in eightString) {
                    if(!temp.contains(j)) {
                        notAvailable += j
                    }
                }
                notAvailable
            }

            val bottom = run {
                val temp = upper + leftTop + middle + rightTop + leftBottom + rightBottom
                var notAvailable = ""
                for(j in eightString) {
                    if(!temp.contains(j)) {
                        notAvailable += j
                    }
                }
                notAvailable
            }

            if(upper.length != 1) {
                println()
                println("--- Found multiple values ($upper) ---")
                println()
            }
            if(leftTop.length != 1) {
                println()
                println("--- Found multiple values ($leftTop) ---")
                println()
            }
            if(rightTop.length != 1) {
                println()
                println("--- Found multiple values ($rightTop) ---")
                println()
            }
            if(middle.length != 1) {
                println()
                println("--- Found multiple values ($middle) ---")
                println()
            }
            if(leftBottom.length != 1) {
                println()
                println("--- Found multiple values ($leftBottom) ---")
                println()
            }
            if(rightBottom.length != 1) {
                println()
                println("--- Found multiple values ($rightBottom) ---")
                println()
            }
            if(bottom.length != 1) {
                println()
                println("--- Found multiple values ($bottom) ---")
                println()
            }

            println("upper: $upper | leftTop: $leftTop | rightTop: $rightTop | middle: $middle\nleftBottom: $leftBottom | rightBottom: $rightBottom | bottom: $bottom")

            val number = run {
                var p2 = ArrayList<Int>()
                var p20 = ""
                for (j in outVal) {
                    if (j.length == 7) {
                        result += "8"
                    } else if (j.length == 6) {
                        result += if (!j.contains(middle)) {
                            "0"
                        } else if (j.contains(rightTop)) {
                            "9"
                        } else if(j.contains(leftBottom)) {
                            "6"
                        }else {
                            " *6* "
                        }
                        p20 += if (!j.contains(middle)) {
                            0
                        } else if (j.contains(rightTop)) {
                            9
                        } else if(j.contains(leftBottom)) {
                            6
                        }else {
                            -1
                        }
                    } else if (j.length == 5) {
                        result += if (j.contains(leftTop)) {
                            "5"
                        } else if (j.contains(leftBottom)) {
                            "2"
                        } else if(j.contains(middle) && j.contains(upper) && j.contains(rightTop) && j.contains(rightBottom)) {
                            "3"
                        }else {
                            " *5* "
                        }
                        p20 += if (j.contains(leftTop)) {
                            5
                        } else if (j.contains(leftBottom)) {
                            2
                        } else if(j.contains(middle) && j.contains(upper) && j.contains(rightTop) && j.contains(rightBottom)) {
                            3
                        }else {
                            -1
                        }
                    } else if (j.length == 4) {
                        result += "4"
                        p20 += 4
                    } else if (j.length == 3) {
                        result += "7"
                        p20 += 7
                    } else if (j.length == 2) {
                        result += "1"
                        p20 += 1
                    } else {
                        result += "*"
                    }
                }
                result += "\n"
            }
        }
        for(i in result.trim().split("\n")) {
            sol += i.toInt()
        }
        return sol.toString()
    }
}
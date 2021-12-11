package de.neo.aoc.days

import java.util.function.Function

class Day09 : AbstractDay() {

    override fun useExampleFile() = true

    private var lines: ArrayList<String> = ArrayList()

    var recursiveFunction: Function<Int, Boolean>? = null

    override fun parseInput() {
        lines = ArrayList(getInput().replace("\r", "").split("\n"))
    }

    fun isBasin(num: Int, i: Int, j: Int): Int {
        val line = lines[0]
        if(i == 0 && j == 0) {
            if(lines[i + 1][j].toString().toInt() > num) {
                if(lines[i][j + 1].toString().toInt() > num) {
                    return 3
                }
            }
        }else if(i == 0 && j == line.length - 1) {
            if(lines[i + 1][j].toString().toInt() > num) {
                if(lines[i][j - 1].toString().toInt() > num) {
                    return 3
                }
            }
        }else if(i == lines.size - 1 && j == 0) {
            if(lines[i - 1][j].toString().toInt() > num) {
                if(lines[i][j + 1].toString().toInt() > num) {
                    return 3
                }
            }
        }else if(i == lines.size - 1 && j == line.length - 1) {
            if(lines[i - 1][j].toString().toInt() > num) {
                if(lines[i][j - 1].toString().toInt() > num) {
                    return 3
                }
            }
        }else if(i == 0) {
            if(lines[i + 1][j].toString().toInt() > num) {
                if(lines[i][j + 1].toString().toInt() > num) {
                    if(lines[i][j - 1].toString().toInt() > num) {
                        return 4
                    }
                }
            }
        }else if(j == 0) {
            if(lines[i + 1][j].toString().toInt() > num) {
                if(lines[i][j + 1].toString().toInt() > num) {
                    if(lines[i - 1][j].toString().toInt() > num) {
                        return 4
                    }
                }
            }
        }else if(i == lines.size - 1) {
            if(lines[i][j - 1].toString().toInt() > num) {
                if(lines[i][j + 1].toString().toInt() > num) {
                    if(lines[i - 1][j].toString().toInt() > num) {
                        return 4
                    }
                }
            }
        }else if(j == line.length - 1) {
            if(lines[i + 1][j].toString().toInt() > num) {
                if(lines[i][j - 1].toString().toInt() > num) {
                    if(lines[i - 1][j].toString().toInt() > num) {
                        return 4
                    }
                }
            }
        } else {
            if(lines[i + 1][j].toString().toInt() > num) {
                if(lines[i][j + 1].toString().toInt() > num) {
                    if(lines[i - 1][j].toString().toInt() > num) {
                        if(lines[i][j - 1].toString().toInt() > num) {
                            return 5
                        }
                    }
                }
            }
        }
        return -1
    }

    override fun part01(): String {
        var risk = 0

        for(i in lines.indices) {
            val line = lines[i]
            for(j in lines[i].indices) {
                val character = line[j]
                val num = character.toString().toInt()
                val basin = isBasin(num, i, j)
                if(basin != -1) {
                    risk += num + 1
                }
            }
        }
        return "risk zone sum: $risk"
    }

    override fun part02(): String {
        var basins = ArrayList<Int>()

        /* Not solved */

        for(i in lines.indices) {
            val line = lines[i]
            for(j in line.indices) {
                val char = line[j]
                var basin = isBasin(char.toString().toInt(), i, j)
                if(basin != -1) {
                    if(basin == 3) {
                        if(i == 0 && j == 0) {
                            for(k in line.indices) {
                            }
                        }
                    }
                }
            }
        }

        return ""
    }
}
package de.neo.aoc.days

import java.util.function.Function

class Day09 : AbstractDay() {

    override fun useExampleFile() = true

    private var lines: ArrayList<String> = ArrayList()

    var recursiveFunction: Function<Int, Boolean>? = null

    override fun parseInput() {
        lines = ArrayList(getInput().replace("\r", "").split("\n"))
    }

    override fun part01(): String {
        var risk = 0

        for(i in lines.indices) {
            val line = lines[i]
            for(j in lines[i].indices) {
                val character = line[j]
                val num = character.toString().toInt()
                if(i == 0 && j == 0) {
                    if(lines[i + 1][j].toString().toInt() > num) {
                        if(lines[i][j + 1].toString().toInt() > num) {
                            risk += num + 1
                        }
                    }
                }else if(i == 0 && j == line.length - 1) {
                    if(lines[i + 1][j].toString().toInt() > num) {
                        if(lines[i][j - 1].toString().toInt() > num) {
                            risk += num + 1
                        }
                    }
                }else if(i == lines.size - 1 && j == 0) {
                    if(lines[i - 1][j].toString().toInt() > num) {
                        if(lines[i][j + 1].toString().toInt() > num) {
                            risk += num + 1
                        }
                    }
                }else if(i == lines.size - 1 && j == line.length - 1) {
                    if(lines[i - 1][j].toString().toInt() > num) {
                        if(lines[i][j - 1].toString().toInt() > num) {
                            risk += num + 1
                        }
                    }
                }else if(i == 0) {
                    if(lines[i + 1][j].toString().toInt() > num) {
                        if(lines[i][j + 1].toString().toInt() > num) {
                            if(lines[i][j - 1].toString().toInt() > num) {
                                risk += num + 1
                            }
                        }
                    }
                }else if(j == 0) {
                    if(lines[i + 1][j].toString().toInt() > num) {
                        if(lines[i][j + 1].toString().toInt() > num) {
                            if(lines[i - 1][j].toString().toInt() > num) {
                                risk += num + 1
                            }
                        }
                    }
                }else if(i == lines.size - 1) {
                    if(lines[i][j - 1].toString().toInt() > num) {
                        if(lines[i][j + 1].toString().toInt() > num) {
                            if(lines[i - 1][j].toString().toInt() > num) {
                                risk += num + 1
                            }
                        }
                    }
                }else if(j == line.length - 1) {
                    if(lines[i + 1][j].toString().toInt() > num) {
                        if(lines[i][j - 1].toString().toInt() > num) {
                            if(lines[i - 1][j].toString().toInt() > num) {
                                risk += num + 1
                            }
                        }
                    }
                } else {
                    if(lines[i + 1][j].toString().toInt() > num) {
                        if(lines[i][j + 1].toString().toInt() > num) {
                            if(lines[i - 1][j].toString().toInt() > num) {
                                if(lines[i][j - 1].toString().toInt() > num) {
                                    risk += num + 1
                                }
                            }
                        }
                    }
                }
            }
        }
        return "risk zone sum: $risk"
    }

    override fun part02(): String {
        var highest = 0
        var middle = 0
        var least = 0

        var counter = 1

        return calculateBasin().toString()
    }

    fun calculateBasin(): Int {
        var size = 0
        for(i in lines.indices) {
            val line = lines[i]
            for(j in line.indices) {
                recursiveFunction = Function {
                    size++
                    if(lines.size > i + 1) {
                        isBasin(i + 1, j, ignore = "north", callback = this.recursiveFunction!!)
                    }
                    if(i - 1 >= 0) {
                        isBasin(i - 1, j, ignore = "south", callback = this.recursiveFunction!!)
                    }
                    if(j - 1 >= 0) {
                        isBasin(i, j - 1, ignore = "east", callback = this.recursiveFunction!!)
                    }
                    if(line.length > j + 1) {
                        isBasin(i, j + 1, ignore = "west", callback = this.recursiveFunction!!)
                    }
                    return@Function true
                }
                isBasin(i, j, callback = this.recursiveFunction!!)
                println("Size: $size")
                if(size >= 1) {
                    return size
                }
            }
        }
        return -1
    }

    private fun isBasin(i: Int, j: Int, ignore: String = "none", callback: Function<Int, Boolean> = Function {
        return@Function true
    }): Boolean {
        val line = lines[i]
        val num = lines[i][j].toString().toInt()
        if(i == 0 && j == 0) {
            if(ignore.contains("south") || lines[i + 1][j].toString().toInt() > num) {
                if(ignore.contains("east") || lines[i][j + 1].toString().toInt() > num) {
                    return callback.apply(num)
                }
            }
        }else if(i == 0 && j == line.length - 1) {
            if(ignore.contains("south") || lines[i + 1][j].toString().toInt() > num) {
                if(ignore.contains("west") || lines[i][j - 1].toString().toInt() > num) {
                    return callback.apply(num)
                }
            }
        }else if(i == lines.size - 1 && j == 0) {
            if(ignore.contains("north") || lines[i - 1][j].toString().toInt() > num) {
                if(ignore.contains("east") || lines[i][j + 1].toString().toInt() > num) {
                    return callback.apply(num)
                }
            }
        }else if(i == lines.size - 1 && j == line.length - 1) {
            if(ignore.contains("north") || lines[i - 1][j].toString().toInt() > num) {
                if(ignore.contains("west") || lines[i][j - 1].toString().toInt() > num) {
                    return callback.apply(num)
                }
            }
        }else if(i == 0) {
            if(ignore.contains("south") || lines[i + 1][j].toString().toInt() > num) {
                if(ignore.contains("east") || lines[i][j + 1].toString().toInt() > num) {
                    if(ignore.contains("west") || lines[i][j - 1].toString().toInt() > num) {
                        return callback.apply(num)
                    }
                }
            }
        }else if(j == 0) {
            if(ignore.contains("south") || lines[i + 1][j].toString().toInt() > num) {
                if(ignore.contains("east") || lines[i][j + 1].toString().toInt() > num) {
                    if(ignore.contains("north") || lines[i - 1][j].toString().toInt() > num) {
                        return callback.apply(num)
                    }
                }
            }
        }else if(i == lines.size - 1) {
            if(ignore.contains("west") || lines[i][j - 1].toString().toInt() > num) {
                if(ignore.contains("east") || lines[i][j + 1].toString().toInt() > num) {
                    if(ignore.contains("north") || lines[i - 1][j].toString().toInt() > num) {
                        return callback.apply(num)
                    }
                }
            }
        }else if(j == line.length - 1) {
            if(ignore.contains("south") || lines[i + 1][j].toString().toInt() > num) {
                if(ignore.contains("west") || lines[i][j - 1].toString().toInt() > num) {
                    if(ignore.contains("north") || lines[i - 1][j].toString().toInt() > num) {
                        return callback.apply(num)
                    }
                }
            }
        } else {
            if(ignore.contains("south") || lines[i + 1][j].toString().toInt() > num) {
                if(ignore.contains("east") || lines[i][j + 1].toString().toInt() > num) {
                    if(ignore.contains("north") || lines[i - 1][j].toString().toInt() > num) {
                        if(ignore.contains("west") || lines[i][j - 1].toString().toInt() > num) {
                            return callback.apply(num)
                        }
                    }
                }
            }
        }
        return false
    }
}
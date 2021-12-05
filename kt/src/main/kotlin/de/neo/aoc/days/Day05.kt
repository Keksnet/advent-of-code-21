package de.neo.aoc.days

import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

class Day05 : AbstractDay() {

    override fun useExampleFile() = false



    override fun parseInput() {

    }

    override fun part01(): String {
        val lines = getInput().replace("\r", "").split("\n")

        val positions = HashMap<String, Int>()
        for(i in lines) {
            val coords = i.replace(" -> ", " ").split(" ")
            val from = ArrayList(coords[0].split(","))
            val to = ArrayList(coords[1].split(","))
            println("from: $from | to: $to")
            if(from[0].toInt() != to[0].toInt()
                && from[1].toInt() != to[1].toInt()) {
                println("Removed")
                continue
            }
            if(from[0].toInt() > to[0].toInt()) {
                val swap = ArrayList(from)
                from[0] = to[0]
                from[1] = to[1]
                to[0] = swap[0]
                to[1] = swap[1]
            }
            if(from[1].toInt() > to[1].toInt()) {
                val swap = ArrayList(from)
                from[0] = to[0]
                from[1] = to[1]
                to[0] = swap[0]
                to[1] = swap[1]
            }
            var over_t = 0
            if(from[0].toInt() == to[0].toInt()) {
                val j = from[0].toInt()
                if(from[1].toInt() == to[1].toInt()) {
                    positions["$j;${from[1].toInt()}"] = (positions["$j;${from[1].toInt()}"] ?: 0) + 1
                    if(positions["$j;${from[1].toInt()}"]!! > 1) {
                        println(++over_t)
                    }
                }else {
                    for(k in from[1].toInt()..to[1].toInt()) {
                        positions["$j;$k"] = (positions["$j;$k"] ?: 0) + 1
                    }
                }
            }else {
                for(j in from[0].toInt()..to[0].toInt()) {
                    if(from[1].toInt() == to[1].toInt()) {
                        positions["$j;${from[1].toInt()}"] = (positions["$j;${from[1].toInt()}"] ?: 0) + 1
                    }else {
                        for(k in from[1].toInt()..to[1].toInt()) {
                            positions["$j;$k"] = (positions["$j;$k"] ?: 0) + 1
                        }
                    }
                }
            }
        }

        var overlapping = 0
        for(i in positions.entries) {
            if(i.value > 1) {
                println("overlapping ${i.key} -> ${i.value}")
                overlapping++
            }
        }

        /*
        for(i in 0..1000) {
            for(j in 0..1000) {
                print(positions["$j;$i"] ?: ".")
            }
            println()
        }
         */

        return "$overlapping overlapping results"
    }

    override fun part02(): String {
        val lines = getInput().replace("\r", "").split("\n")

        val positions = HashMap<String, Int>()
        for(i in lines) {
            val coords = i.replace(" -> ", " ").split(" ")
            val from = ArrayList(coords[0].split(","))
            val to = ArrayList(coords[1].split(","))
            println("from: $from | to: $to")
            var diagonal = false
            var xChange = 0
            var yChange = 0
            if(from[0].toInt() > to[0].toInt()) {
                val swap = ArrayList(from)
                from[0] = to[0]
                from[1] = to[1]
                to[0] = swap[0]
                to[1] = swap[1]
            }else if(from[1].toInt() > to[1].toInt()) {
                val swap = ArrayList(from)
                from[0] = to[0]
                from[1] = to[1]
                to[0] = swap[0]
                to[1] = swap[1]
            }
            if(from[0].toInt() != to[0].toInt()
                && from[1].toInt() != to[1].toInt()) {
                diagonal = true
                xChange = (to[0].toInt() - from[0].toInt()) / abs(to[1].toInt() - from[1].toInt())
                yChange = (to[1].toInt() - from[1].toInt()) / abs(to[0].toInt() - from[0].toInt())
            }
            if(from[0].toInt() == to[0].toInt()) {
                val j = from[0].toInt()
                if(from[1].toInt() == to[1].toInt()) {
                    positions["$j;${from[1].toInt()}"] = (positions["$j;${from[1].toInt()}"] ?: 0) + 1
                }else {
                    for(k in from[1].toInt()..to[1].toInt()) {
                        positions["$j;$k"] = (positions["$j;$k"] ?: 0) + 1
                    }
                }
            }else if(diagonal) {
                var x = from[0].toInt()
                var y = from[1].toInt()
                while(true) {
                    positions["$x;$y"] = (positions["$x;$y"] ?: 0) + 1
                    if(x == to[0].toInt()) {
                        break
                    }
                    x += xChange
                    y += yChange
                }
            } else {
                for(j in from[0].toInt()..to[0].toInt()) {
                    if(from[1].toInt() == to[1].toInt()) {
                        positions["$j;${from[1].toInt()}"] = (positions["$j;${from[1].toInt()}"] ?: 0) + 1
                    }else {
                        for(k in from[1].toInt()..to[1].toInt()) {
                            positions["$j;$k"] = (positions["$j;$k"] ?: 0) + 1
                        }
                    }
                }
            }
        }

        var highestX = 0
        var highestY = 0
        for(x in 0..1000) {
            if(x > highestX) {
                highestX = x
            }
        }
        for(y in 0..1000) {
            if(y > highestY) {
                highestY = y
            }
        }

        var overlapping = 0
        for(i in positions.entries) {
            if(i.value > 1) {
                overlapping++
            }
        }

        Files.writeString(Path.of("day05_vis.html"), buildString {
            append("""
		<html>
		<head>
                <style>
                * {
                    font-family: "Courier New", Courier, monospace;
		    background-color: #333;
                }
                
                .val-0 {
                    color: grey;
                }
                
                .val-1 {
                    color: yellow;
                }
                
                .val-2 {
                    color: orange;
                }
                
                .val-3 {
                    color: crimson;
                }
                
                .val-4 {
                    color: red;
                }
                
                .val-5 {
                    color: darkred;
                }

		.val-high {
		    color: black;
		}
                </style>
		</head>
		<body>
            """.trimIndent())
            for(y in 0 until highestY) {
                for(x in 0 until highestX) {
                    append(when(positions["$x;$y"]) {
                        0 -> "<a class=\"val-0\">.</a>"
                        null -> "<a class=\"val-0\">.</a>"

                        1 -> "<a class=\"val-1\">1</a>"

                        2 -> "<a class=\"val-2\">2</a>"

                        3 -> "<a class=\"val-3\">3</a>"

                        4 -> "<a class=\"val-4\">4</a>"

                        5 -> "<a class=\"val-5\">5</a>"

                        else -> "<a class=\"val-high\">${positions["$x;$y"]}</a>"
                    })
                }
                append("<br>\n")
            }
	    append("</body>\n</html>")
        })

        return "$overlapping overlapping positions found"
    }
}
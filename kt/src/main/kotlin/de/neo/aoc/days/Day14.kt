package de.neo.aoc.days

class Day14 : AbstractDay() {

    override fun useExampleFile() = true

    private var template = ""
    private val replaceWith = HashMap<String, String>()

    override fun parseInput() {
        val parts = getInput().replace("\r", "").split("\n\n")
        template = parts[0]

        val lines = parts[1].split("\n")
        lines.forEach {
            val replace = it.replace(" -> ", "#").split("#")
            replaceWith[replace[0]] = replace[1]
        }
    }

    private fun replace(template: String): String {
        val replaced = StringBuilder()
        for(i in template.indices) {
            if(i == template.length - 1) continue
            if("${template[i]}${template[i + 1]}" in replaceWith) {
                if(i == 0) {
                    replaced.append("${template[i]}${replaceWith["${template[i]}${template[i + 1]}"]}${template[i + 1]}")
                }else {
                    replaced.append("${replaceWith["${template[i]}${template[i + 1]}"]}${template[i + 1]}")
                }
            }
        }
        return replaced.toString().trim()
    }

    override fun part01(): String {
        val replaced = run {
            var last = template
            for(i in 0 until 10) {
                last = replace(last)
            }
            return@run last
        }
        val counts = HashMap<String, Int>()
        counts["HIGHEST"] = 0
        counts["LOWEST"] = 1000000000

        replaceWith.forEach { it0 ->
            counts[it0.value] = replaced.count  {
                return@count it == it0.value[0]
            }
        }

        var highest = "HIGHEST"
        var lowest = "LOWEST"

        for(i in counts) {
            if(i.key == "HIGHEST" || i.key == "LOWEST") continue
            if(i.value > counts[highest]!!) {
                highest = i.key
            }
            if(i.value < counts[lowest]!!) {
                lowest = i.key
            }
        }

        //println("$lowest $highest $counts")

        val magic = counts[highest]!! - counts[lowest]!!

        return "magic: $magic"
    }

    override fun part02(): String {
        // Again I put this on a machine running linux
        val replaced = run {
            var last = template
            for(i in 0 until 40) {
                println("replace: $i")
                last = replace(last)
            }
            return@run last
        }
        val counts = HashMap<String, Int>()
        counts["HIGHEST"] = 0
        counts["LOWEST"] = 1000000000

        replaceWith.forEach { it0 ->
            counts[it0.value] = replaced.count  {
                return@count it == it0.value[0]
            }
        }

        var highest = "HIGHEST"
        var lowest = "LOWEST"

        for(i in counts) {
            if(i.key == "HIGHEST" || i.key == "LOWEST") continue
            if(i.value > counts[highest]!!) {
                highest = i.key
            }
            if(i.value < counts[lowest]!!) {
                lowest = i.key
            }
        }

        //println("$lowest $highest $counts")

        val magic = counts[highest]!! - counts[lowest]!!

        return "magic: $magic"
    }
}
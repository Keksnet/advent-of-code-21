package de.neo.aoc.days.day11

class Octopus(private var energy: Int) {

    var init = false
    private var chain = false

    fun tick(chain: Boolean): Boolean {
        if(this.chain && chain && !init) {
            if(!init) {
                //println("Octopus is not init: $energy | $chain | $init")
            }
            return false
        }
        this.chain = chain
        energy++
        if(energy > 9) {
            energy = 0
            return true
        }
        return false
    }

    fun getEnergy(): Int {
        return energy
    }

}
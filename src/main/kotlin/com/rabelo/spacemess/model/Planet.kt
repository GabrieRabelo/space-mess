package com.rabelo.spacemess.model

class Planet(val id: Int, width: Int, height: Int) {

    private val positions: Array<Array<Position>>

    init {
        positions = Array(width) { Array(height) { Position() } }
        for (y in 0 until height) {
            for (x in 0 until width){
                val current = positions[y][x]
                current.x = x
                current.y = y
            }
        }
    }


    override fun toString(): String {
        var allPositions = ""
        for (row in positions) {
            allPositions += row.contentToString() + "\n"
        }
        return "Planet(id=$id, positions=${allPositions})"
    }

}

fun main() {
    var a = Planet(id=1, 5, 5)
    println(a)
}
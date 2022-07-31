package com.rabelo.spacemess.model

import com.rabelo.spacemess.exception.IllegalPositionException

class Planet(
    var id: Int? = null,
    var positions: Array<Array<Position>>
) {

    override fun toString(): String {
        var allPositions = ""
        for (row in positions) {
            allPositions += row.contentToString() + "\n"
        }
        return "Planet(id=$id, positions=${allPositions})"
    }

    fun landProbe(probe: SpaceProbe, x: Int, y: Int): Position {
        if(isPositionValid(x, y)) {
            val position = positions[x][y]
            position.probe = probe
            return position
        } else throw IllegalPositionException("User tried to land probe into an invalid position.")
    }

    private fun isPositionValid(x: Int, y: Int): Boolean {
        if(y < 0 || y >= positions.size || x < 0 || x >= positions[0].size) {
            return false
        }
        return true
    }

    fun moveProbe(probe: SpaceProbe): Position {
        var x = probe.position?.x
        var y = probe.position?.y

        if (x != null && y != null) {
            when (probe.direction) {
                Direction.NORTH -> y += 1
                Direction.EAST -> x += 1
                Direction.SOUTH -> y -= 1
                Direction.WEST -> x -= 1
            }
        }

        val newPosition = positions[x!!][y!!]
        newPosition.probe = probe
        return newPosition
    }
}
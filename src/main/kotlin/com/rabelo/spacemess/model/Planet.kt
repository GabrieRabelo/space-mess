package com.rabelo.spacemess.model

import com.rabelo.spacemess.exception.IllegalPositionException
import com.rabelo.spacemess.exception.ProbeNotFoundException

class Planet(
    private var id: Int? = null,
    var positions: Array<Array<Position>>
) {

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
        val actualXPosition = probe.position?.x!!
        val actualYPosition = probe.position?.y!!

        if (positions[actualXPosition][actualYPosition].probe != probe) {
            throw ProbeNotFoundException("Probe is not landed at given position in this planet.")
        }

        // Remove probe from previous position
        positions[actualXPosition][actualYPosition].probe = null

        var newXPosition = actualXPosition
        var newYPosition = actualYPosition

        // Change x or y point depending on probe direction
        when (probe.direction) {
            Direction.NORTH -> newYPosition += 1
            Direction.EAST -> newXPosition += 1
            Direction.SOUTH -> newYPosition -= 1
            Direction.WEST -> newXPosition -= 1
        }

        // Defines new probe position and return it
        if (!isPositionValid(newXPosition, newYPosition)) {
            throw IllegalPositionException("User tried to move the probe into an invalid position.")
        }

        val newPosition = positions[newXPosition][newYPosition]
        newPosition.probe = probe
        return newPosition
    }

    override fun toString(): String {
        var occupiedPosition = ""
        for (row in positions) {
            for(pos in row) {
                if(pos.probe != null) {
                    occupiedPosition += pos.toString() + "\n"
                }
            }
        }
        return "Planet(id=$id, occupiedPositions=${occupiedPosition})"
    }
}
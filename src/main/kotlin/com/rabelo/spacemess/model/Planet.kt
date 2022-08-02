package com.rabelo.spacemess.model

import com.rabelo.spacemess.exception.IllegalPositionException
import com.rabelo.spacemess.exception.ProbeAlreadyOnPlanetException
import java.awt.Point
import java.lang.RuntimeException

class Planet(
    private var id: Int? = null,
    var longitude: Int,
    var latitude: Int,
    var obstacles: MutableList<Obstacle> = mutableListOf()
) {

    fun landProbe(probe: SpaceProbe, point: Point): Point {

        validatePosition(point)
        validateDuplicatedProbe(probe)

        obstacles.add(probe)

        return point

    }

    private fun validateDuplicatedProbe(probe: SpaceProbe) {
        if (obstacles.any { it == probe }) {
            throw ProbeAlreadyOnPlanetException("User tried to land a probe that already is on the planet.")
        }
    }

    fun validatePosition(point: Point) {
        if (point.x < 0 || point.x >= this.longitude || point.y < 0 || point.y >= latitude) {
            throw IllegalPositionException("User tried to land probe into an invalid latitude or longitude.")
        }
        if (obstacles.any { it.position == point }) {
            throw IllegalPositionException("User tried to land probe into an obstacle.")
        }
    }

    fun calculateNextStep(probe: SpaceProbe): Point {
        var newX = probe.position!!.x
        var newY = probe.position!!.y
        when (probe.direction) {
            Direction.NORTH -> {
                if (newY + 1 > this.latitude) {
                    newY = 0
                } else newY +=1
            }
            Direction.EAST -> {
                if (newX + 1 > this.longitude) {
                    newX = 0
                } else newX +=1
            }
            Direction.SOUTH -> {
                if (newY - 1 < 0) {
                    newY = this.latitude
                } else newY -=1
            }
            Direction.WEST -> {
                if (newX - 1 < 0) {
                    newX = this.longitude
                } else newX -=1
            }

            else -> {
                throw RuntimeException("")
            } // TODO add an exception
        }

        return Point(newX, newY)
    }

}
package com.rabelo.spacemess.model

import com.rabelo.spacemess.exception.IllegalPositionException
import com.rabelo.spacemess.exception.ProbeAlreadyOnPlanetException
import java.awt.Point

class Planet(
    private var id: Int? = null,
    var width: Int? = null,
    var height: Int? = null,
    var probes: MutableList<SpaceProbe> = mutableListOf()
) {

    fun landProbe(probe: SpaceProbe, point: Point) {

        validateDuplicatedProbe(probe)
        validatePosition(point)

        probes.add(probe)
    }

    fun validatePosition(point: Point) {
        if (point.x < 0 || point.x >= this.width!! || point.y < 0 || point.y >= height!!) {
            throw IllegalPositionException("User tried to land probe into an invalid area.")
        }
        if (probes.any { it.position == point }) {
            throw IllegalPositionException("User tried to land probe into another probe.")
        }
    }

    private fun validateDuplicatedProbe(probe: SpaceProbe) {
        if (probes.any { it == probe }) {
            throw ProbeAlreadyOnPlanetException("User tried to land a probe that already is on the planet.")
        }
    }

}
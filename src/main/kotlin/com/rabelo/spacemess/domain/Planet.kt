package com.rabelo.spacemess.domain

import com.rabelo.spacemess.exception.IllegalPositionException
import com.rabelo.spacemess.exception.ProbeAlreadyOnPlanetException
import java.awt.Point
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Planet(
    var width: Int? = null,
    var height: Int? = null,
    @OneToMany
    var probes: MutableList<SpaceProbe> = mutableListOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
) {

    fun landProbe(probe: SpaceProbe, point: Point) {

        validateDuplicatedProbe(probe)
        validateAreaBoundaries(point)
        validateObstacle(point)

        probes.add(probe)
    }

    fun validateAreaBoundaries(point: Point) {
        if (point.x < 0 || point.x >= this.width!! || point.y < 0 || point.y >= height!!) {
            throw IllegalPositionException("User tried to land probe into an invalid area.")
        }
    }

    fun validateObstacle(point: Point) {
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
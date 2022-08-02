package com.rabelo.spacemess.model

import com.rabelo.spacemess.exception.ProbeNotLandedException
import java.awt.Point

class SpaceProbe(
    private val id: String ?= null,
    var direction: Direction ?= null): Obstacle() {

    fun turnRight() {
        this.direction = this.direction?.right()
    }

    fun turnLeft() {
        this.direction = this.direction?.left()
    }

    fun landProbe(planet: Planet, point: Point, direction: Direction) {
        planet.landProbe(this, point)
        this.planet = planet
        this.position = point
        this.direction = direction
    }

    fun move() {
        if(planet == null) {
            throw ProbeNotLandedException("User tried to move a probe that is not on a planet.")
        }
        val newPosition = this.planet!!.calculateNextStep(this)
        this.planet!!.validatePosition(this.position!!)
        this.position = newPosition
    }

    override fun toString(): String {
        return "SpaceProbe(id='$id', direction=$direction)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpaceProbe

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }


}
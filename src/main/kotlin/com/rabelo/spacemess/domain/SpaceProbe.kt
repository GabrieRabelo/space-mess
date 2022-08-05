package com.rabelo.spacemess.domain

import com.rabelo.spacemess.exception.ProbeNotLandedException
import com.rabelo.spacemess.exception.InvalidDirectionException
import java.awt.Point
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class SpaceProbe(
    var direction: Direction? = null,
    var position: Point? = null,
    @ManyToOne
    var planet: Planet? = null,
    @Id
    @GeneratedValue
    var id: Int? = null
) {

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

    fun receiveCommand(command: String) {
        for (char in command) {
            when(char) {
                'L' -> this.turnLeft()
                'R' -> this.turnRight()
                'M' -> this.move()
            }
        }
    }

    fun move() {
        if (planet == null) {
            throw ProbeNotLandedException("User tried to move a probe that is not on a planet.")
        }
        val newPosition = this.calculateNextStep()
        this.planet!!.validateObstacle(newPosition)
        this.position = newPosition
    }

    private fun calculateNextStep(): Point {
        var newX = this.position!!.x
        var newY = this.position!!.y

        when (this.direction) {
            Direction.NORTH -> {
                if (newY + 1 > this.planet?.height!!) {
                    newY = 0
                } else newY += 1
            }

            Direction.EAST -> {
                if (newX + 1 > this.planet?.width!!) {
                    newX = 0
                } else newX += 1
            }

            Direction.SOUTH -> {
                if (newY - 1 < 0) {
                    newY = this.planet?.height!!
                } else newY -= 1
            }

            Direction.WEST -> {
                if (newX - 1 < 0) {
                    newX = this.planet?.width!!
                } else newX -= 1
            }

            else -> {
                throw InvalidDirectionException("Invalid direction on SpaceProbe, verify probe configuration")
            }
        }

        return Point(newX, newY)
    }

    fun isLanded() : Boolean {
        return planet != null
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
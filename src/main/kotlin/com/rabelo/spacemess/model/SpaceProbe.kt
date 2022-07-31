package com.rabelo.spacemess.model

class SpaceProbe(
    private val id: String ?= null,
    var direction: Direction,
    private val planet: Planet ?= null,
    var position: Position ?= null
){

    fun turnRight() {
        this.direction = this.direction.right()
    }

    fun turnLeft() {
        this.direction = this.direction.left()
    }

    fun move() {
        this.position = planet?.moveProbe(this)
    }

    override fun toString(): String {
        return "SpaceProbe(id='$id', direction=$direction)"
    }
}
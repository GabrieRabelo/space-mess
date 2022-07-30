package com.rabelo.spacemess.model

class SpaceProbe(private val id: String, private var direction: Direction){

    fun turnRight() {
        this.direction = this.direction.right()
    }

    fun turnLeft() {
        this.direction = this.direction.left()
    }

    fun move() {
        // TODO: implement move method
    }

    override fun toString(): String {
        return "SpaceProbe(id='$id', direction=$direction)"
    }

}
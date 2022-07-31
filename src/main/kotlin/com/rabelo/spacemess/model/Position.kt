package com.rabelo.spacemess.model

class Position(var probe: SpaceProbe? = null,
               var x: Int,
               var y: Int) {

    override fun toString(): String {
        return "Position(probe=$probe, x=$x, y=$y)"
    }
}

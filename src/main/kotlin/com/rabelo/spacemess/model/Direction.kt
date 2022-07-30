package com.rabelo.spacemess.model

/*
 A direction is to where the SpaceProbe is pointed. All direction implements a left and right
 method that points which direction will be the next or previous.
 */

enum class Direction {

    NORTH {
        override fun left() = EAST
        override fun right() = WEST
    },

    EAST {
        override fun left() = NORTH
        override fun right() = SOUTH
    },

    SOUTH {
        override fun left() = EAST
        override fun right() = WEST
    },

    WEST {
        override fun left() = SOUTH
        override fun right() = WEST
    };

    abstract fun left(): Direction
    abstract fun right(): Direction

}
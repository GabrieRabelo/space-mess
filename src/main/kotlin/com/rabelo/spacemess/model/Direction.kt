package com.rabelo.spacemess.model

enum class Direction {

    NORTH {
        override fun left() = WEST
        override fun right() = EAST
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
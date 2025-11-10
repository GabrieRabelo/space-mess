package com.rabelo.spacemess.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DirectionTest {

    @Test
    fun `when direction is NORTH left should be WEST and right should be EAST`() {

        val direction = Direction.NORTH

        assertThat(direction.left()).isEqualTo(Direction.WEST)
        assertThat(direction.right()).isEqualTo(Direction.EAST)
    }

    @Test
    fun `when direction is EAST left should be NORTH and right should be SOUTH`() {

        val direction = Direction.EAST

        assertThat(direction.left()).isEqualTo(Direction.NORTH)
        assertThat(direction.right()).isEqualTo(Direction.SOUTH)
    }

    @Test
    fun `when direction is SOUTH left should be EAST and right should be WEST`() {

        val direction = Direction.SOUTH

        assertThat(direction.left()).isEqualTo(Direction.EAST)
        assertThat(direction.right()).isEqualTo(Direction.WEST)
    }

    @Test
    fun `when direction is WEST left should be NORTH and right should be NORTH`() {

        val direction = Direction.WEST

        assertThat(direction.left()).isEqualTo(Direction.SOUTH)
        assertThat(direction.right()).isEqualTo(Direction.NORTH)
    }
}
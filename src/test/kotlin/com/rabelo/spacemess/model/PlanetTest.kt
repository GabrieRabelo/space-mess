package com.rabelo.spacemess.model

import com.rabelo.spacemess.exception.IllegalPositionException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test

internal class PlanetTest {

    @Test
    fun `test landProbe when position is valid should land probe into given position`(){
        // Given
        val width = 5
        val height = 5
        val positions = Array(width) { y -> Array (height) { x -> Position(x = x, y = y)} }

        val planet = Planet(positions = positions)
        val probe = SpaceProbe(direction = Direction.NORTH)

        // When
        planet.landProbe(probe, 3, 4)

        // Then
        assertThat(planet.positions[3][4].probe).isEqualTo(probe)
    }

    @Test
    fun `test landProbe when position is out of bounds should throw illegalPositionException`(){
        // Given
        val width = 5
        val height = 5
        val positions = Array(width) { y -> Array (height) { x -> Position(x = x, y = y)} }

        val planet = Planet(positions = positions)
        val probe = SpaceProbe(direction = Direction.NORTH)

        // When / Then
        assertThatCode { planet.landProbe(probe, 3, 5) }.isInstanceOf(IllegalPositionException::class.java)

    }

}
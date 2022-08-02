package com.rabelo.spacemess.model

import com.rabelo.spacemess.exception.IllegalPositionException
import com.rabelo.spacemess.exception.ProbeNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
import java.awt.Point

internal class PlanetTest {

    @Test
    fun `test landProbe when there is not an obstacle should land probe into given position`() {
        // Given
        val planet = Planet(latitude = 5, longitude = 5)
        val probe = SpaceProbe(direction = Direction.NORTH)
        val position = Point(3, 4)

        // When
        planet.landProbe(probe, position)

        // Then
        assertThat(planet.obstacles).contains(probe)
    }

    @Test
    fun `test landProbe when position is out of bounds should throw IllegalPositionException`() {
        // Given
        val planet = Planet(latitude = 5, longitude = 5)
        val probe = SpaceProbe(direction = Direction.NORTH)
        val position = Point(6, 6)

        // When / Then
        assertThatCode {
            planet.landProbe(probe, position)
        }.isInstanceOf(IllegalPositionException::class.java)
    }

}
package com.rabelo.spacemess.domain

import com.rabelo.spacemess.exception.IllegalPositionException
import com.rabelo.spacemess.exception.ProbeAlreadyOnPlanetException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
import java.awt.Point

internal class PlanetTest {

    @Test
    fun `test landProbe when is possible to land should land probe into given position`() {
        // Given
        val planet = Planet(height = 5, width = 5)
        val probe = SpaceProbe(direction = Direction.NORTH)
        val position = Point(3, 4)

        // When
        planet.landProbe(probe, position)

        // Then
        assertThat(planet.probes).contains(probe)
    }

    @Test
    fun `test landProbe when probe is already on planet should throw ProbeAlreadyOnPlanetException`() {
        // Given
        val planet = Planet(height = 5, width = 5)
        val probe = SpaceProbe(id = 1, direction = Direction.NORTH)
        val position = Point(3, 4)

        planet.landProbe(probe, position)

        // When / Then
        assertThatCode { planet.landProbe(probe, position) }
            .isInstanceOf(ProbeAlreadyOnPlanetException::class.java)
            .hasMessage("User tried to land a probe that already is on the planet.")
    }


    @Test
    fun `test landProbe when position is out of bounds should throw IllegalPositionException`() {
        // Given
        val planet = Planet(height = 5, width = 5)
        val probe = SpaceProbe(direction = Direction.NORTH)
        val position = Point(6, 6)

        // When / Then
        assertThatCode { planet.landProbe(probe, position) }
            .isInstanceOf(IllegalPositionException::class.java)
            .hasMessage("User tried to land probe into an invalid area.")
    }

}
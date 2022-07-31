package com.rabelo.spacemess.model

import com.rabelo.spacemess.exception.IllegalPositionException
import com.rabelo.spacemess.exception.ProbeNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test

internal class PlanetTest {

    @Test
    fun `test landProbe when position is valid should land probe into given position`(){
        // Given
        val width = 5
        val height = 5
        val positions = Array(width) { x -> Array (height) { y -> Position(x = x, y = y)} }

        val planet = Planet(positions = positions)
        val probe = SpaceProbe(direction = Direction.NORTH)

        // When
        planet.landProbe(probe, 3, 4)

        // Then
        assertThat(planet.positions[3][4].probe).isEqualTo(probe)
    }

    @Test
    fun `test landProbe when position is out of bounds should throw IllegalPositionException`(){
        // Given
        val width = 5
        val height = 5
        val positions = Array(width) { x -> Array (height) { y -> Position(x = x, y = y)} }

        val planet = Planet(positions = positions)
        val probe = SpaceProbe(direction = Direction.NORTH)

        // When / Then
        assertThatCode { planet.landProbe(probe, 3, 5) }.isInstanceOf(IllegalPositionException::class.java)
    }

    @Test
    fun `test moveProbe when position valid should move the probe into the new position`(){
        // Given
        val width = 5
        val height = 5
        val positions = Array(width) { x -> Array (height) { y -> Position(x = x, y = y)} }

        val planet = Planet(positions = positions)
        val probe = SpaceProbe(direction = Direction.NORTH)
        val position = planet.positions[3][3]
        position.probe = probe
        probe.position = position

        // When
        planet.moveProbe(probe)

        assertThat(planet.positions[3][4].probe).isEqualTo(probe)
        assertThat(planet.positions[3][3].probe).isNull()
    }

    @Test
    fun `test moveProbe when position does not have a probe should throw ProbeNotFoundException`(){
        // Given
        val width = 5
        val height = 5
        val positions = Array(width) { x -> Array (height) { y -> Position(x = x, y = y)} }

        val planet = Planet(positions = positions)
        val probe = SpaceProbe(direction = Direction.NORTH)
        val position = planet.positions[4][4]
        probe.position = position

        // When / Then
        assertThatCode { planet.moveProbe(probe) }.isInstanceOf(ProbeNotFoundException::class.java)

    }

}
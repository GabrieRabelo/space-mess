package com.rabelo.spacemess.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import java.awt.Point

@ExtendWith(MockitoExtension::class)
internal class SpaceProbeTest {

    @ParameterizedTest
    @EnumSource(Direction::class)
    fun `test turnRight should turn right`(direction: Direction) {
        val probe = SpaceProbe(direction = direction)

        probe.turnRight()

        assertThat(probe.direction).isEqualTo(direction.right())
    }

    @ParameterizedTest
    @EnumSource(Direction::class)
    fun `test turnLeft should change direction to left`(direction: Direction) {
        val probe = SpaceProbe(direction = direction)

        probe.turnLeft()

        assertThat(probe.direction).isEqualTo(direction.left())
    }

    @Test
    fun `test landProbe should land successfully on planet`() {

        // Given
        val planetMock : Planet = mock()

        val probe = SpaceProbe()
        val position = Point(3,3)
        val expectedProbe = SpaceProbe(direction = Direction.NORTH, position = position, planet = planetMock)

        doNothing().`when`(planetMock).landProbe(probe, position)

        // When
        probe.landProbe(planetMock, Point(3,3), Direction.NORTH)

        // Then
        assertThat(probe)
            .usingRecursiveComparison()
            .isEqualTo(expectedProbe)
    }

    @Test
    fun `test move should move successfully to NORTH`() {
        // Given
        val planetMock : Planet = mock()
        val position = Point(3,2)
        val probe = SpaceProbe(direction = Direction.NORTH, position = position, planet = planetMock)

        val expectedPosition = Point(3,3)
        val expectedProbe = SpaceProbe(direction = Direction.NORTH, position = expectedPosition, planet = planetMock)

        `when`(planetMock.height).thenReturn(5)
        doNothing().`when`(planetMock).validateObstacle(expectedPosition)

        // When
        probe.move()

        // Then
        assertThat(probe)
            .usingRecursiveComparison()
            .isEqualTo(expectedProbe)
    }

    @Test
    fun `test move when probe is at y axis higher limit should move to lower limit`() {
        // Given
        val planetMock : Planet = mock()
        val position = Point(3,5)
        val probe = SpaceProbe(direction = Direction.NORTH, position = position, planet = planetMock)

        val expectedPosition = Point(3,0)
        val expectedProbe = SpaceProbe(direction = Direction.NORTH, position = expectedPosition, planet = planetMock)

        `when`(planetMock.height).thenReturn(5)
        doNothing().`when`(planetMock).validateObstacle(expectedPosition)

        // When
        probe.move()

        // Then
        assertThat(probe)
            .usingRecursiveComparison()
            .isEqualTo(expectedProbe)
    }

    @Test
    fun `test move when y axis is at lower limit should move to higher limit`() {
        // Given
        val planetMock : Planet = mock()
        val position = Point(3,0)
        val probe = SpaceProbe(direction = Direction.SOUTH, position = position, planet = planetMock)

        val newPosition = Point(3,5)
        val expectedProbe = SpaceProbe(direction = Direction.SOUTH, position = newPosition, planet = planetMock)

        `when`(planetMock.height).thenReturn(5)
        doNothing().`when`(planetMock).validateObstacle(newPosition)

        // When
        probe.move()

        // Then
        assertThat(probe)
            .usingRecursiveComparison()
            .isEqualTo(expectedProbe)
    }

    @Test
    fun `test move should move successfully on x axis`() {
        // Given
        val planetMock : Planet = mock()
        val position = Point(3,3)
        val probe = SpaceProbe(direction = Direction.WEST, position = position, planet = planetMock)
        val newPosition = Point(2,3)
        val expectedProbe = SpaceProbe(direction = Direction.WEST, position = newPosition, planet = planetMock)

        doNothing().`when`(planetMock).validateObstacle(newPosition)

        // When
        probe.move()

        // Then
        assertThat(probe)
            .usingRecursiveComparison()
            .isEqualTo(expectedProbe)
    }

    @Test
    fun `test move when probe is at x axis higher limit should move to lower limit`() {
        // Given
        val planetMock : Planet = mock()
        val position = Point(5,3)
        val probe = SpaceProbe(direction = Direction.EAST, position = position, planet = planetMock)

        val expectedPosition = Point(0,3)
        val expectedProbe = SpaceProbe(direction = Direction.EAST, position = expectedPosition, planet = planetMock)

        doNothing().`when`(planetMock).validateObstacle(expectedPosition)

        // When
        probe.move()

        // Then
        assertThat(probe)
            .usingRecursiveComparison()
            .isEqualTo(expectedProbe)
    }

    @Test
    fun `test move when x axis is at lower limit should move to higher limit`() {
        // Given
        val planetMock : Planet = mock()
        val position = Point(0,3)
        val probe = SpaceProbe(direction = Direction.WEST, position = position, planet = planetMock)

        val expectedPosition = Point(5,3)
        val expectedProbe = SpaceProbe(direction = Direction.WEST, position = expectedPosition, planet = planetMock)

        `when`(planetMock.width).thenReturn(5)
        doNothing().`when`(planetMock).validateObstacle(expectedPosition)

        // When
        probe.move()

        // Then
        assertThat(probe)
            .usingRecursiveComparison()
            .isEqualTo(expectedProbe)
    }
}
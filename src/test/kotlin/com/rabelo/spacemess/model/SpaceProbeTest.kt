package com.rabelo.spacemess.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.mockito.junit.jupiter.MockitoExtension

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
}
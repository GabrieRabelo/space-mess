package com.rabelo.spacemess.repository

import com.rabelo.spacemess.domain.Direction
import com.rabelo.spacemess.domain.Planet
import com.rabelo.spacemess.domain.SpaceProbe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.awt.Point

@DataJpaTest
class RepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: PlanetRepository,
    val spaceProbeRepository: SpaceProbeRepository
) {

    @Test
    fun `verify planet entity is correctly saved on database`() {
        val planet = entityManager.persist(Planet(5, 5))
        val probe = entityManager.persist(SpaceProbe(Direction.NORTH, Point(2,3), planet))
        planet.probes = mutableListOf(probe)
        entityManager.flush()

        val found = userRepository.findById(planet.id!!).get()

        assertThat(found).usingRecursiveComparison().isEqualTo(planet)
    }

    @Test
    fun `verify space probe entity is correctly saved on database`() {
        val planet = entityManager.persist(Planet(5, 5))
        val probe = entityManager.persist(SpaceProbe(Direction.NORTH, Point(2,3), planet))
        planet.probes = mutableListOf(probe)
        entityManager.flush()

        val found = spaceProbeRepository.findById(probe.id!!).get()

        assertThat(found).usingRecursiveComparison().isEqualTo(probe)
    }

}
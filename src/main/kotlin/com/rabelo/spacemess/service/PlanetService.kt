package com.rabelo.spacemess.service

import com.rabelo.spacemess.controller.PlanetController
import com.rabelo.spacemess.converter.PlanetConverter
import com.rabelo.spacemess.service.dto.CreatePlanetDTO
import com.rabelo.spacemess.service.dto.PlanetResponseDTO
import com.rabelo.spacemess.repository.PlanetRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PlanetService(
    private val planetRepository: PlanetRepository,
    private val planetConverter: PlanetConverter
) {

    fun createPlanet(createPlanetDTO: CreatePlanetDTO): PlanetResponseDTO {
        return planetConverter.fromCreatePlanetDTO(createPlanetDTO)
            .let { planetRepository.save(it) }
            .let { planetConverter.toPlanetResponse(it) }
            .also { logger.debug("Successfully created a planet. Response: {}", it) }
    }

    fun findAllPlanets(): List<PlanetResponseDTO> {
        return planetRepository.findAll()
            .map { planetConverter.toPlanetResponse(it) }
            .toList()
            .also { logger.debug("Successfully returned all planets.") }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlanetController::class.java)
    }
}
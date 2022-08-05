package com.rabelo.spacemess.service

import com.rabelo.spacemess.controller.converter.PlanetConverter
import com.rabelo.spacemess.controller.dto.CreatePlanetDTO
import com.rabelo.spacemess.controller.dto.PlanetResponseDTO
import com.rabelo.spacemess.repository.PlanetRepository
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
    }

    fun findAllPlanets(): List<PlanetResponseDTO> {
        return planetRepository.findAll()
            .map { planetConverter.toPlanetResponse(it) }
            .toList()
    }
}
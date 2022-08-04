package com.rabelo.spacemess.service

import com.rabelo.spacemess.controller.converter.PlanetConverter
import com.rabelo.spacemess.controller.dto.CreatePlanetDTO
import com.rabelo.spacemess.domain.Planet
import com.rabelo.spacemess.repository.PlanetRepository
import org.springframework.stereotype.Service

@Service
class PlanetService(
    private val planetRepository: PlanetRepository,
    private val planetConverter: PlanetConverter
) {

    fun createPlanet(createPlanetDTO: CreatePlanetDTO) : Planet{
        val entity = planetConverter.fromCreatePlanetDTO(createPlanetDTO)
        return planetRepository.save(entity)
    }
}
package com.rabelo.spacemess.converter

import com.rabelo.spacemess.service.dto.CreatePlanetDTO
import com.rabelo.spacemess.service.dto.PlanetResponseDTO
import com.rabelo.spacemess.domain.Planet
import org.springframework.stereotype.Component

@Component
class PlanetConverter {

    fun fromCreatePlanetDTO(createPlanetDTO: CreatePlanetDTO) : Planet{
        return Planet(height = createPlanetDTO.height, width = createPlanetDTO.width)
    }

    fun toPlanetResponse(planet: Planet) : PlanetResponseDTO {
        return PlanetResponseDTO(planet.width!!, planet.height!!, planet.id!!)
    }
}
package com.rabelo.spacemess.controller.converter

import com.rabelo.spacemess.controller.dto.CreatePlanetDTO
import com.rabelo.spacemess.domain.Planet
import org.springframework.stereotype.Component

@Component
class PlanetConverter {

    fun fromCreatePlanetDTO(createPlanetDTO: CreatePlanetDTO) : Planet{
        return Planet(height = createPlanetDTO.height, width = createPlanetDTO.width)
    }
}
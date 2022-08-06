package com.rabelo.spacemess.converter

import com.rabelo.spacemess.service.dto.SpaceProbeResponseDTO
import com.rabelo.spacemess.domain.SpaceProbe
import org.springframework.stereotype.Component

@Component
class SpaceProbeConverter(val planetConverter: PlanetConverter) {


    fun toSpaceProbeResponse(spaceProbe: SpaceProbe): SpaceProbeResponseDTO {
        return SpaceProbeResponseDTO(
            id = spaceProbe.id,
            direction = spaceProbe.direction,
            position = spaceProbe.position,
            planet = spaceProbe.planet?.let { planetConverter.toPlanetResponse(it) }
        )
    }
}
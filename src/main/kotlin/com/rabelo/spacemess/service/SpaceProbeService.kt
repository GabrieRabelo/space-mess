package com.rabelo.spacemess.service

import com.rabelo.spacemess.controller.dto.SpaceProbeResponseDTO
import com.rabelo.spacemess.converter.SpaceProbeConverter
import com.rabelo.spacemess.domain.SpaceProbe
import com.rabelo.spacemess.repository.SpaceProbeRepository
import org.springframework.stereotype.Service

@Service
class SpaceProbeService(
    private val spaceProbeRepository: SpaceProbeRepository,
    private val spaceProbeConverter: SpaceProbeConverter
) {

    fun createSpaceProbe(): SpaceProbeResponseDTO {
        return spaceProbeRepository.save(SpaceProbe())
            .let { spaceProbeConverter.toSpaceProbeResponse(it) }
    }

    fun findAllSpaceProbes(): List<SpaceProbeResponseDTO> {
        return spaceProbeRepository.findAll()
            .map { spaceProbeConverter.toSpaceProbeResponse(it) }
            .toList()
    }
}
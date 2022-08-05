package com.rabelo.spacemess.service

import com.rabelo.spacemess.controller.dto.LandProbeRequestDTO
import com.rabelo.spacemess.controller.dto.SendCommandRequestDTO
import com.rabelo.spacemess.controller.dto.SpaceProbeResponseDTO
import com.rabelo.spacemess.converter.SpaceProbeConverter
import com.rabelo.spacemess.domain.SpaceProbe
import com.rabelo.spacemess.exception.IllegalPositionException
import com.rabelo.spacemess.exception.ProbeAlreadyOnPlanetException
import com.rabelo.spacemess.repository.PlanetRepository
import com.rabelo.spacemess.repository.SpaceProbeRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.webjars.NotFoundException
import java.awt.Point

@Service
class SpaceProbeService(
    private val spaceProbeRepository: SpaceProbeRepository,
    private val spaceProbeConverter: SpaceProbeConverter,
    private val planetRepository: PlanetRepository
) {

    fun createSpaceProbe(): SpaceProbeResponseDTO {
        return spaceProbeRepository.save(SpaceProbe())
            .let { spaceProbeConverter.toSpaceProbeResponse(it) }
            .also { logger.debug("Successfully created space probe. Response: {}", it) }
    }

    fun findAllSpaceProbes(): List<SpaceProbeResponseDTO> {
        return spaceProbeRepository.findAll()
            .map { spaceProbeConverter.toSpaceProbeResponse(it) }
            .toList()
            .also { logger.debug("Successfully listed all probes." ) }
    }

    fun landProbe(probeId: Int, landProbeRequest: LandProbeRequestDTO): SpaceProbeResponseDTO {
        val planet = planetRepository.findById(landProbeRequest.planetId).orElseGet { throw NotFoundException("") }
        val probe = spaceProbeRepository.findById(probeId).orElseGet { throw NotFoundException("") }

        if (probe.isLanded()) {
            val errorMessage = "Probe is already landed on a planet.";
            logger.error(errorMessage)
            throw ProbeAlreadyOnPlanetException(errorMessage)
        }

        probe.landProbe(
            planet = planet,
            point = Point(landProbeRequest.x, landProbeRequest.y),
            direction = landProbeRequest.direction
        )

        return spaceProbeConverter.toSpaceProbeResponse(spaceProbeRepository.save(probe))
            .also { logger.debug("Successfully landed space probe. Response: {}", it) }
    }

    fun sendCommand(probeId: Int, sendCommandRequestDTO: SendCommandRequestDTO): SpaceProbeResponseDTO {
        val probe = spaceProbeRepository.findById(probeId).orElseGet { throw NotFoundException("") }

        try{
            probe.receiveCommand(sendCommandRequestDTO.command)
        } catch (ex: IllegalPositionException) {
            logger.warn("Probe {} detected an obstacle, stop moving.", probeId)
        }

        return spaceProbeRepository.save(probe)
            .let { spaceProbeConverter.toSpaceProbeResponse(probe) }
            .also { logger.debug("Successfully received command succesfully space probe. Response: {}", it) }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(SpaceProbeService::class.java);
    }
}
package com.rabelo.spacemess.controller

import com.rabelo.spacemess.controller.dto.LandProbeRequestDTO
import com.rabelo.spacemess.controller.dto.SendCommandRequestDTO
import com.rabelo.spacemess.controller.dto.SpaceProbeResponseDTO
import com.rabelo.spacemess.service.SpaceProbeService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/space-probes")
class SpaceProbeController(private val spaceProbeService: SpaceProbeService) {

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Create a space probe")
    fun createSpaceProbe(): ResponseEntity<SpaceProbeResponseDTO> {
        logger.info("Creating space probe.")
        return ResponseEntity(spaceProbeService.createSpaceProbe(), HttpStatus.CREATED)
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Retrieve a space probe list")
    fun findSpaceProbes(): ResponseEntity<List<SpaceProbeResponseDTO>> {
        logger.info("Finding all space probes.")
        return ResponseEntity(spaceProbeService.findAllSpaceProbes(), HttpStatus.OK)
    }

    @PatchMapping("/{id}/land")
    @ApiResponse(responseCode = "200", description = "Land a probe in a planet")
    fun landProbe(
        @PathVariable @Valid id: Int,
        @RequestBody @Valid landProbeRequestDTO: LandProbeRequestDTO
    ): ResponseEntity<SpaceProbeResponseDTO> {
        logger.info("Landing probe {} with request {}.", id, landProbeRequestDTO)
        return ResponseEntity(spaceProbeService.landProbe(id, landProbeRequestDTO), HttpStatus.OK)
    }

    @PatchMapping("/{id}/move")
    @ApiResponse(responseCode = "200", description = "Move a probe in a planet")
    fun moveProbe(
        @PathVariable @Valid id: Int,
        @RequestBody @Valid sendCommandRequestDTO: SendCommandRequestDTO
    ): ResponseEntity<SpaceProbeResponseDTO> {
        logger.info("Sending command to probe {} with request {}.", id, sendCommandRequestDTO)
        return ResponseEntity(spaceProbeService.sendCommand(id, sendCommandRequestDTO), HttpStatus.OK)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(SpaceProbeController::class.java);
    }
}
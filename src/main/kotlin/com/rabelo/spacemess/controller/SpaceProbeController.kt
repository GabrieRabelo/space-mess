package com.rabelo.spacemess.controller

import com.rabelo.spacemess.controller.dto.SpaceProbeResponseDTO
import com.rabelo.spacemess.service.SpaceProbeService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/space-probes")
class SpaceProbeController(private val spaceProbeService: SpaceProbeService){

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Create a space probe")
    fun createSpaceProbe() : ResponseEntity<SpaceProbeResponseDTO> {
        return ResponseEntity(spaceProbeService.createSpaceProbe(), HttpStatus.CREATED)
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Retrieve a space probe list")
    fun findSpaceProbes() : ResponseEntity<List<SpaceProbeResponseDTO>> {
        return ResponseEntity(spaceProbeService.findAllSpaceProbes(), HttpStatus.OK)
    }
}
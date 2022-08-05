package com.rabelo.spacemess.controller

import com.rabelo.spacemess.controller.dto.CreatePlanetDTO
import com.rabelo.spacemess.controller.dto.PlanetResponseDTO
import com.rabelo.spacemess.service.PlanetService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/planets")
class PlanetController(val planetService: PlanetService){

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Create a planet")
    fun createPlanet(@RequestBody @Valid createPlanetDTO: CreatePlanetDTO) : ResponseEntity<PlanetResponseDTO> {
        logger.info("Creating planet.")
        return ResponseEntity(planetService.createPlanet(createPlanetDTO), HttpStatus.CREATED)
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Retrieve a planet list")
    fun findAllPlanets() : ResponseEntity<List<PlanetResponseDTO>> {
        logger.info("Finding all planets.")
        return ResponseEntity(planetService.findAllPlanets(), HttpStatus.OK)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlanetController::class.java);
    }
}
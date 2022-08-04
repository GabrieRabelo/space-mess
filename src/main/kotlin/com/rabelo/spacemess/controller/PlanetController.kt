package com.rabelo.spacemess.controller

import com.rabelo.spacemess.controller.dto.CreatePlanetDTO
import com.rabelo.spacemess.domain.Planet
import com.rabelo.spacemess.service.PlanetService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class PlanetController(val planetService: PlanetService){

    @PostMapping("/planets")
    @ApiResponse(responseCode = "201", description = "Create a planet")
    fun createPlanet(@RequestBody @Valid createPlanetDTO: CreatePlanetDTO) : ResponseEntity<Planet> {
        return ResponseEntity(planetService.createPlanet(createPlanetDTO), HttpStatus.CREATED)
    }

    @GetMapping("/planets")
    @ApiResponse(responseCode = "200", description = "Retrieve a planet list")
    fun findAllPlanets() : ResponseEntity<MutableIterable<Planet>> {
        return ResponseEntity(planetService.findAllPlanets(), HttpStatus.OK)
    }
}
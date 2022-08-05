package com.rabelo.spacemess.controller.dto

import com.rabelo.spacemess.domain.Direction
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class LandProbeRequestDTO(
    @field:Positive val planetId: Int,
    @field:Positive val x: Int,
    @field:Positive val y: Int,
    @field:NotNull val direction: Direction
)
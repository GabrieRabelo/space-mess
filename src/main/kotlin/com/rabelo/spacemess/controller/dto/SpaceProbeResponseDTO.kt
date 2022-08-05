package com.rabelo.spacemess.controller.dto

import com.rabelo.spacemess.domain.Direction
import java.awt.Point

data class SpaceProbeResponseDTO (
    var direction: Direction ? = null,
    var position: Point ? = null,
    var planet: PlanetResponseDTO ? = null,
    var id: Int ? = null
)
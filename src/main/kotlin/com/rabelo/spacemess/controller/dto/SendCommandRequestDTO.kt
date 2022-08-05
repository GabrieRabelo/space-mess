package com.rabelo.spacemess.controller.dto

import javax.validation.constraints.Pattern

class SendCommandRequestDTO(
    @field:Pattern(regexp = "^[LRM]+\$") val command: String
)
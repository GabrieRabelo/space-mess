package com.rabelo.spacemess.service.dto

import javax.validation.constraints.Pattern

class SendCommandRequestDTO(
    @field:Pattern(regexp = "^[LRM]+\$") val command: String
)
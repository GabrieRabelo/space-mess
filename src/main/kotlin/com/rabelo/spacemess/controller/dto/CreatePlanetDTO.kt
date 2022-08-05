package com.rabelo.spacemess.controller.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class CreatePlanetDTO(@field:[Positive NotNull] val height: Int,
                      @field:[Positive NotNull] val width: Int)
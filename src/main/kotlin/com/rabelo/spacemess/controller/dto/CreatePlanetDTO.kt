package com.rabelo.spacemess.controller.dto

import javax.validation.constraints.Positive

class CreatePlanetDTO(@Positive val height: Int,
                      @Positive val width: Int)
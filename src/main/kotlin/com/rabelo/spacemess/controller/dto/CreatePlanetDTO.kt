package com.rabelo.spacemess.controller.dto

import javax.validation.constraints.Positive

class CreatePlanetDTO(@field:Positive val height: Int?= null,
                      @field:Positive val width: Int?= null)
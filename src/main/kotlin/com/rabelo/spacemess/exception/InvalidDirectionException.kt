package com.rabelo.spacemess.exception

import java.lang.RuntimeException

class InvalidDirectionException(override val message: String?): RuntimeException() {
}
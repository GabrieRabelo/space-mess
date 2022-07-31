package com.rabelo.spacemess.exception

import java.lang.RuntimeException

class IllegalPositionException(override val message: String?): RuntimeException() {
}
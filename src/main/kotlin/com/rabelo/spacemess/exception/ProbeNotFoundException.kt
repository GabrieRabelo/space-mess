package com.rabelo.spacemess.exception

import java.lang.RuntimeException

class ProbeNotFoundException(override val message: String?): RuntimeException() {
}
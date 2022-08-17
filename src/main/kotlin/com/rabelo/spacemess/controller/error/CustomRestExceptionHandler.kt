package com.rabelo.spacemess.controller.error

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.webjars.NotFoundException


@ControllerAdvice
class CustomRestExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(
        ex: NotFoundException, request: WebRequest?
    ): ResponseEntity<Any> {

        val apiError = ApiError(HttpStatus.NOT_FOUND, ex.localizedMessage, ex.message!!)
        return ResponseEntity<Any>(
            apiError, HttpHeaders(), apiError.status
        )
    }
}
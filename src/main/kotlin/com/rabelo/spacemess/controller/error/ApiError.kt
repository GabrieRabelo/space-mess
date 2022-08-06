package com.rabelo.spacemess.controller.error

import org.springframework.http.HttpStatus

class ApiError {
    var status: HttpStatus
    var message: String
    var errors: List<String>

    constructor(status: HttpStatus, message: String, errors: List<String>) {
        this.status = status
        this.message = message
        this.errors = errors
    }

    constructor(status: HttpStatus, message: String, error: String?) {
        this.status = status
        this.message = message
        error.let { errors = arrayListOf(it!!) }
    }
}
package com.calyrsoft.client.exception

import org.springframework.http.HttpStatus

data class ErrorBody(
    val message: String,
    val detail: String,
    val errors: List<String>,
    val status: HttpStatus,
    val statusCode: Int
)

open class ErrorResponse(val body: ErrorBody): Exception()

class UserNotFoundException(override var message: String, var detail: String, var errors: List<String>): ErrorResponse(
    body = ErrorBody(
        message = message,
        detail = detail,
        errors = errors,
        status = HttpStatus.NOT_FOUND,
        statusCode = HttpStatus.NOT_FOUND.value()
    )
)

class BadRequestException(override var message: String, var detail: String, var errors: List<String>): ErrorResponse(
    body = ErrorBody(
        message = message,
        detail = detail,
        errors = errors,
        status = HttpStatus.BAD_REQUEST,
        statusCode = HttpStatus.BAD_REQUEST.value()
    )
)

class InternalServerErrorException(override var message: String, val detail: String): ErrorResponse(
    body = ErrorBody(
        message = message,
        detail = detail,
        errors = emptyList(),
        status = HttpStatus.INTERNAL_SERVER_ERROR,
        statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value()
    )
)

class ServiceUnavailableException(override var message: String, val detail: String): ErrorResponse(
    body = ErrorBody(
        message = message,
        detail = detail,
        errors = emptyList(),
        status = HttpStatus.SERVICE_UNAVAILABLE,
        statusCode = HttpStatus.SERVICE_UNAVAILABLE.value()
    )
)



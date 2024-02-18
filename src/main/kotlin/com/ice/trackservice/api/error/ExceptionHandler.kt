package com.ice.trackservice.api.error

import com.ice.trackservice.domain.service.ServiceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant.now

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<APIError>? {
        e.printStackTrace()
        return ResponseEntity<APIError>(APIError(e.message ?: "", now()), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(ServiceException::class)
    fun handleAPIException(e: ServiceException): ResponseEntity<APIError>? {
        return ResponseEntity<APIError>(
            APIError(e.message, e.timestamp),
            HttpStatus.INTERNAL_SERVER_ERROR // TODO update
        )
    }
}
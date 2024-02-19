package com.ice.trackservice.api.error

import com.ice.trackservice.domain.service.ArtistNotFoundException
import com.ice.trackservice.domain.service.GenreNotFoundException
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
        return ResponseEntity<APIError>(APIError(e.message ?: "", now()),
            EXCEPTION_TO_STATUS_CODE[e::class] ?: HttpStatus.INTERNAL_SERVER_ERROR)
    }

    companion object {
        val EXCEPTION_TO_STATUS_CODE = mapOf(
            IllegalArgumentException::class to HttpStatus.BAD_REQUEST,
            GenreNotFoundException::class to HttpStatus.BAD_REQUEST,
            ArtistNotFoundException::class to HttpStatus.NOT_FOUND
        )
    }
}
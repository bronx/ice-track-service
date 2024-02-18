package com.ice.trackservice.domain.service

import java.time.Instant
import java.time.Instant.now

abstract class ServiceException(
    override val message: String,
    val timestamp: Instant = now()
): RuntimeException()

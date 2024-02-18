package com.ice.trackservice.api.error

import java.time.Instant

data class APIError(val message: String, val timestamp: Instant)

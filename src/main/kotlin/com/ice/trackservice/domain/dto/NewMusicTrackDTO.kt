package com.ice.trackservice.domain.dto

import java.util.*

data class NewMusicTrackDTO(val genreId: UUID, val title: String, val length: Int, val metadata: Map<String, String>)
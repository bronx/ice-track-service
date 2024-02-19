package com.ice.trackservice.domain.dto

import java.util.*

data class MusicTrackDTO(
    val id: UUID,
    val genre: GenreDTO,
    val title: String,
    val length: Int,
    val metadata: Map<String, String>
)
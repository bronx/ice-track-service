package com.ice.trackservice.mapper

import com.ice.trackservice.api.controller.GenreDTO
import com.ice.trackservice.api.controller.MusicTrackDTO
import com.ice.trackservice.domain.model.MusicTrack

fun MusicTrack.toDTO() = MusicTrackDTO(
    id = this.id,
    genre = GenreDTO(this.genre.id, this.genre.name),
    title = this.title,
    length = this.length,
    metadata = this.metadata.map { it.key to it.value }.toMap()
)
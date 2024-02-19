package com.ice.trackservice.mapper

import com.ice.trackservice.domain.dto.ArtistDTO
import com.ice.trackservice.domain.dto.GenreDTO
import com.ice.trackservice.domain.dto.MusicTrackDTO
import com.ice.trackservice.domain.model.Artist
import com.ice.trackservice.domain.model.MusicTrack

fun MusicTrack.toDTO() = MusicTrackDTO(
    id = this.id,
    genre = GenreDTO(this.genre.id, this.genre.name),
    title = this.title,
    length = this.length,
    metadata = this.metadata.map { it.key to it.value }.toMap()
)

fun Set<MusicTrack>.toDTO() = this.map { it.toDTO() }

fun Artist.toDTO() = ArtistDTO(this.id, this.currentAlias.alias, this.aliases.map { it.alias }.toSet())
fun Iterable<Artist>.toDTO() = this.map { it.toDTO() }
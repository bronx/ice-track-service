package com.ice.trackservice.domain.dto

import java.util.*

data class ArtistDTO(val id: UUID, val name: String, val aliases: Set<String>)
data class NewAliasDTO(val alias: String)
data class NewMusicTrackDTO(val genreId: UUID, val title: String, val length: Int, val metadata: Map<String, String>)
data class MusicTrackDTO(
    val id: UUID,
    val genre: GenreDTO,
    val title: String,
    val length: Int,
    val metadata: Map<String, String>
)
data class GenreDTO(val id: UUID, val name: String)
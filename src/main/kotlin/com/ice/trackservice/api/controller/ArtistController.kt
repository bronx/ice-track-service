package com.ice.trackservice.api.controller

import com.ice.trackservice.domain.service.ArtistService
import com.ice.trackservice.mapper.toDTO
import jakarta.persistence.*
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/artists")
class ArtistController(private val artistService: ArtistService) {

    @PostMapping("/{id}/tracks")
    fun addMusicTrack(
        @PathVariable id: UUID,
        @RequestBody newMusicTrack: NewMusicTrackDTO
    ) = artistService.addTrack(id, newMusicTrack).toDTO()

}

data class NewMusicTrackDTO(val genreId: UUID, val title: String, val length: Int, val metadata: Map<String, String>)
data class MusicTrackDTO(
    val id: UUID,
    val genre: GenreDTO,
    val title: String,
    val length: Int,
    val metadata: Map<String, String>
)
data class GenreDTO(val id: UUID, val name: String)

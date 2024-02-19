package com.ice.trackservice.api.controller

import com.ice.trackservice.domain.dto.NewAliasDTO
import com.ice.trackservice.domain.dto.NewMusicTrackDTO
import com.ice.trackservice.domain.service.ArtistService
import com.ice.trackservice.mapper.toDTO
import jakarta.persistence.*
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/artists")
class ArtistController(private val artistService: ArtistService) {

    @GetMapping
    fun getArtists(@RequestParam(required = false) ofTheDay: Boolean = false) =
        artistService.getArtists(ofTheDay).toDTO()

    @GetMapping("/{id}")
    fun getArtist(@PathVariable id: UUID) = artistService.getArtist(id).toDTO()

    @PostMapping("/{id}/alias")
    fun addAlias(
        @PathVariable id: UUID,
        @RequestBody newAliasDTO: NewAliasDTO
    ) = artistService.addAlias(id, newAliasDTO.alias).toDTO()

    @PostMapping("/{id}/tracks")
    fun addMusicTrack(
        @PathVariable id: UUID,
        @RequestBody newMusicTrack: NewMusicTrackDTO
    ) = artistService.addTrack(id, newMusicTrack).toDTO()

    @GetMapping("/{id}/tracks")
    fun getTracks(@PathVariable id: UUID) = artistService.getTracks(id).toDTO()
}

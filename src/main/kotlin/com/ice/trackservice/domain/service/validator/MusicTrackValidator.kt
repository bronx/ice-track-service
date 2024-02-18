package com.ice.trackservice.domain.service.validator

import com.ice.trackservice.api.controller.NewMusicTrackDTO
import com.ice.trackservice.domain.model.Artist
import com.ice.trackservice.domain.repository.MusicTrackRepository
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
class MusicTrackValidator(private val musicTrackRepository: MusicTrackRepository) {
    fun validate(newMusicTrack: NewMusicTrackDTO, artist: Artist) = when {
        newMusicTrack.title.isBlank() -> throw IllegalArgumentException("Track title cannot be empty nor blank!")
        newMusicTrack.length <= 0 -> throw IllegalArgumentException("Invalid track length! (${newMusicTrack.length})")
        musicTrackRepository.existsByArtistAndTitle(artist, newMusicTrack.title)
            -> throw IllegalArgumentException("Duplicated track! (${newMusicTrack.title})")
        else -> Unit
    }
}

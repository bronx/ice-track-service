package com.ice.trackservice.domain.service

import com.ice.trackservice.api.controller.NewMusicTrackDTO
import com.ice.trackservice.domain.model.MusicTrack
import com.ice.trackservice.domain.repository.ArtistRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArtistService(
    private val artistRepository: ArtistRepository,
    private val musicTrackService: MusicTrackService
) {

    fun addTrack(artistId: UUID, newMusicTrackDTO: NewMusicTrackDTO): MusicTrack {
        val artist = artistRepository.findByIdOrNull(artistId) ?: throw ArtistNotFoundException(artistId)
        return musicTrackService.new(newMusicTrackDTO, artist)
    }
}

class ArtistNotFoundException(artistId: UUID): ServiceException("Artist ($artistId) not found!")
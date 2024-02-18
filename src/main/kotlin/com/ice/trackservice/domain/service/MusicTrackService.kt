package com.ice.trackservice.domain.service

import com.ice.trackservice.api.controller.NewMusicTrackDTO
import com.ice.trackservice.domain.model.Artist
import com.ice.trackservice.domain.model.MusicTrack
import com.ice.trackservice.domain.model.MusicTrackMetadata
import com.ice.trackservice.domain.repository.MusicGenreRepository
import com.ice.trackservice.domain.repository.MusicTrackMetadataRepository
import com.ice.trackservice.domain.repository.MusicTrackRepository
import com.ice.trackservice.domain.service.validator.MusicTrackValidator
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class MusicTrackService(
    private val musicTrackRepository: MusicTrackRepository,
    private val metadataRepository: MusicTrackMetadataRepository,
    private val genreRepository: MusicGenreRepository,
    private val musicTrackValidator: MusicTrackValidator
) {

    @Transactional
    fun new(newMusicTrackDTO: NewMusicTrackDTO, artist: Artist): MusicTrack {
        val genre = genreRepository.findByIdOrNull(newMusicTrackDTO.genreId)
            ?: throw GenreNotFoundException(newMusicTrackDTO.genreId)

        musicTrackValidator.validate(newMusicTrackDTO, artist)

        return MusicTrack(
            artist = artist,
            genre = genre,
            title = newMusicTrackDTO.title,
            length = newMusicTrackDTO.length
        ).let { musicTrack ->
            musicTrackRepository.save(musicTrack).let {
                val metadata = newMusicTrackDTO.metadata.map { entry ->
                    MusicTrackMetadata(
                        track = it,
                        key = entry.key,
                        value = entry.value
                    )
                }.toSet()
                if (metadata.isNotEmpty()) {
                    metadataRepository.saveAll(metadata)
                    it.copy(metadata = metadata)
                } else {
                    it
                }
            }
        }
    }
}

class GenreNotFoundException(genreId: UUID): ServiceException("Genre ($genreId) not found!")
package com.ice.trackservice.domain.service

import com.ice.trackservice.domain.dto.NewMusicTrackDTO
import com.ice.trackservice.domain.model.Artist
import com.ice.trackservice.domain.model.ArtistAlias
import com.ice.trackservice.domain.model.MusicTrack
import com.ice.trackservice.domain.repository.ArtistAliasRepository
import com.ice.trackservice.domain.repository.ArtistRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate.now
import java.util.*

@Service
class ArtistService(
    private val artistRepository: ArtistRepository,
    private val artistAliasRepository: ArtistAliasRepository,
    private val musicTrackService: MusicTrackService
) {
    fun getArtists(ofTheDay: Boolean): Iterable<Artist> {
        if (!ofTheDay) return artistRepository.findAll()

        val totalArtists = artistRepository.count()
        if (totalArtists == 0L) return emptySet()

        val today = now()
        val lastTimeOnSpotlight = today.minusDays(totalArtists)
        return (artistRepository.findByLastSpotlightDay(today) // already selected for today!
            ?: artistRepository.findFirstByLastSpotlightDayIsNull() // never selected before!
            ?: artistRepository.findByLastSpotlightDay(lastTimeOnSpotlight)) // repeating - artist might miss a day if any artist is deleted!
            ?.let { artistOfTheDay ->
                if (today != artistOfTheDay.lastSpotlightDay) {
                    artistRepository.save(artistOfTheDay.copy(lastSpotlightDay = today))
                }
                setOf(artistOfTheDay)
            } ?: emptySet()

    }
    fun getArtist(artistId: UUID) = artistRepository.findByIdOrNull(artistId) ?: throw ArtistNotFoundException(artistId)

    fun addAlias(artistId: UUID, alias: String): Artist {
        val artist = getArtist(artistId)
        return artistAliasRepository.save(ArtistAlias(artist = artist, alias = alias)).let {
            artistRepository.save(artist.copy(currentAlias = it))
        }
    }

    fun addTrack(artistId: UUID, newMusicTrackDTO: NewMusicTrackDTO): MusicTrack {
        val artist = getArtist(artistId)
        return musicTrackService.new(newMusicTrackDTO, artist)
    }

    fun getTracks(artistId: UUID) = getArtist(artistId).tracks
}

class ArtistNotFoundException(artistId: UUID): ServiceException("Artist ($artistId) not found!")
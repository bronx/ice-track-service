package com.ice.trackservice.domain.service

import com.ice.trackservice.domain.dto.NewMusicTrackDTO
import com.ice.trackservice.domain.model.Artist
import com.ice.trackservice.domain.model.ArtistAlias
import com.ice.trackservice.domain.model.MusicTrack
import com.ice.trackservice.domain.repository.ArtistAliasRepository
import com.ice.trackservice.domain.repository.ArtistRepository
import jakarta.transaction.Transactional
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
    @Transactional
    fun getArtists(ofTheDay: Boolean): Iterable<Artist> {
        if (!ofTheDay) return artistRepository.findAll()

        val today = now()
        return (artistRepository.findByLastSpotlightDay(today) // already selected for today!
            ?: artistRepository.findFirstByLastSpotlightDayIsNull() // never selected before!
            ?: this.findOldestInSpotlight()) // gets the one that was farthest back in past!
            ?.let { artistOfTheDay ->
                if (today != artistOfTheDay.lastSpotlightDay) {
                    artistRepository.save(artistOfTheDay.copy(lastSpotlightDay = today))
                }
                setOf(artistOfTheDay)
            } ?: emptySet()

    }

    private fun findOldestInSpotlight(): Artist? = artistRepository.findMinSpotlightDay()?.let {
        artistRepository.findByLastSpotlightDay(it)
    }

    fun getArtist(artistId: UUID) = artistRepository.findByIdOrNull(artistId) ?: throw ArtistNotFoundException(artistId)

    @Transactional
    fun addAlias(artistId: UUID, alias: String): Artist {
        val artist = getArtist(artistId)
        if (alias.isBlank()) throw IllegalArgumentException("Alias cannot be empty nor blank!")
        return findOrCreateAlias(artist, alias.trim()).let {
            artistRepository.save(artist.copy(currentAlias = it))
        }
    }

    private fun findOrCreateAlias(artist: Artist, alias: String): ArtistAlias {
        return artistAliasRepository.findByArtistAndAlias(artist, alias)  // could have searched through artist.aliases
            ?: artistAliasRepository.save(ArtistAlias(artist = artist, alias = alias))
    }

    fun addTrack(artistId: UUID, newMusicTrackDTO: NewMusicTrackDTO): MusicTrack {
        val artist = getArtist(artistId)
        return musicTrackService.new(newMusicTrackDTO, artist)
    }

    fun getTracks(artistId: UUID) = getArtist(artistId).tracks
}

class ArtistNotFoundException(artistId: UUID): ServiceException("Artist ($artistId) not found!")
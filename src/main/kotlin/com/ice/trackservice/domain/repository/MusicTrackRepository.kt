package com.ice.trackservice.domain.repository

import com.ice.trackservice.domain.model.Artist
import com.ice.trackservice.domain.model.MusicTrack
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MusicTrackRepository: CrudRepository<MusicTrack, UUID> {
    fun existsByArtistAndTitle(artist: Artist, title: String): Boolean
}

package com.ice.trackservice.domain.repository

import com.ice.trackservice.domain.model.Artist
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate
import java.util.*

interface ArtistRepository: CrudRepository<Artist, UUID> {
    fun findFirstByLastSpotlightDayIsNull(): Artist?
    fun findByLastSpotlightDay(spotlightDay: LocalDate): Artist?
}
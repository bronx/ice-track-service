package com.ice.trackservice.domain.repository

import com.ice.trackservice.domain.model.Artist
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate
import java.util.*

interface ArtistRepository: CrudRepository<Artist, UUID> {
    fun findFirstByLastSpotlightDayIsNull(): Artist?
    @Query("select min(a.lastSpotlightDay) from Artist a")
    fun findMinSpotlightDay(): LocalDate?
    fun findByLastSpotlightDay(spotlightDay: LocalDate): Artist?
}
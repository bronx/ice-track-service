package com.ice.trackservice.domain.model

import jakarta.persistence.*
import java.time.Instant
import java.time.Instant.now
import java.time.LocalDate
import java.util.*
import java.util.UUID.randomUUID

@Entity
@Table
data class Artist(
    @Id val id: UUID = randomUUID(),
    @OneToMany(mappedBy = "artist") val aliases: Set<ArtistAlias>,
    @OneToOne @JoinColumn(name = "current_alias_id") val currentAlias: ArtistAlias,
    @Column(nullable = false) val createdAt: Instant = now(),
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "artist") val tracks: Set<MusicTrack>,
    @Column(unique = true) val lastSpotlightDay: LocalDate? = null
) {
    override fun hashCode() = Objects.hash(id, createdAt)
}
package com.ice.trackservice.domain.model

import jakarta.persistence.*
import java.time.Instant
import java.time.Instant.now
import java.util.UUID
import java.util.UUID.randomUUID

@Entity
@Table
data class Artist(
    @Id val id: UUID = randomUUID(),
    @OneToMany(mappedBy = "artist") val aliases: Set<ArtistAlias>,
    @OneToOne @JoinColumn(name = "current_alias_id") val currentAlias: ArtistAlias,
    val createAt: Instant = now(),
    @Version @Column(nullable = false) val timestamp: Instant = now()
)
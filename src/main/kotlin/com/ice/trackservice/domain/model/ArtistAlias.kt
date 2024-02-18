package com.ice.trackservice.domain.model

import jakarta.persistence.*
import java.util.*
import java.util.UUID.randomUUID

@Entity
@Table(name = "artist_alias", uniqueConstraints = [
    UniqueConstraint(columnNames = [
        "artist_id",
        "alias"
    ])
])
data class ArtistAlias(
    @Id val id: UUID = randomUUID(),
    @ManyToOne @JoinColumn(name = "artist_id", nullable = false) val artist: Artist,
    @Column(nullable = false) val alias: String
)
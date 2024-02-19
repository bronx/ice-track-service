package com.ice.trackservice.domain.model

import jakarta.persistence.*
import java.time.Instant
import java.util.UUID
import java.util.UUID.randomUUID

@Entity
@Table(name = "music_track", uniqueConstraints = [
    UniqueConstraint(columnNames = [
        "artist_id",
        "title"
    ])
])
data class MusicTrack(
    @Id val id: UUID = randomUUID(),
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "artist_id", nullable = false) val artist: Artist,
    @ManyToOne @JoinColumn(name = "genre_id", nullable = false) val genre: MusicGenre,
    @Column(nullable = false) val title: String,
    @Column(nullable = false) val length: Int, // in seconds
    @OneToMany(mappedBy = "track", fetch = FetchType.EAGER) val metadata: Set<MusicTrackMetadata> = emptySet(),
    @Column(nullable = false) val createdAt: Instant = Instant.now()
)
package com.ice.trackservice.domain.model

import jakarta.persistence.*
import java.util.UUID
import java.util.UUID.randomUUID

@Entity
@Table(name = "music_track")
data class MusicTrack(
    @Id val id: UUID = randomUUID(),
    @ManyToOne @JoinColumn(name = "artist_id", nullable = false) val artist: Artist,
    @ManyToOne @JoinColumn(name = "genre_id", nullable = false) val genre: MusicGenre,
    @Column(nullable = false) val title: String,
    @Column(nullable = false) val length: Int, // in seconds
    @OneToMany(mappedBy = "track") val metadata: Set<MusicTrackMetadata>
)
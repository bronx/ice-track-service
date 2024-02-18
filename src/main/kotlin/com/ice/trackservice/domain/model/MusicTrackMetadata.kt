package com.ice.trackservice.domain.model

import jakarta.persistence.*
import java.util.UUID
import java.util.UUID.randomUUID

@Entity
@Table(name = "music_track_metadata", uniqueConstraints = [
    UniqueConstraint(columnNames = [
        "music_track_id",
        "metadata_key",
        "metadata_value"
    ])
])
data class MusicTrackMetadata(
    @Id val id: UUID = randomUUID(),
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "music_track_id", nullable = false) val track: MusicTrack,
    @Column(name = "metadata_key", nullable = false) val key: String,
    @Column(name = "metadata_value", nullable = false) val value: String
)
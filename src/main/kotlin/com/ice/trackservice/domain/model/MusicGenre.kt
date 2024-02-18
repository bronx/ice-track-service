package com.ice.trackservice.domain.model

import jakarta.persistence.*
import java.util.UUID
import java.util.UUID.randomUUID

@Entity
@Table(name = "music_genre")
data class MusicGenre(
    @Id val id: UUID = randomUUID(),
    @Column(name = "name", nullable = false) val name: String
)
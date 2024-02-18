package com.ice.trackservice.domain.service

import com.ice.trackservice.domain.repository.ArtistRepository
import org.springframework.stereotype.Service

@Service
class ArtistService(private val artistRepository: ArtistRepository)
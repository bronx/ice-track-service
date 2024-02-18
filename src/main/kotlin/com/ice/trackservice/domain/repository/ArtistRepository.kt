package com.ice.trackservice.domain.repository

import com.ice.trackservice.domain.model.Artist
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ArtistRepository: CrudRepository<Artist, UUID>
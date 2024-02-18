package com.ice.trackservice.domain.repository

import com.ice.trackservice.domain.model.MusicGenre
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MusicGenreRepository: CrudRepository<MusicGenre, UUID>

package com.ice.trackservice.domain.repository

import com.ice.trackservice.domain.model.ArtistAlias
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ArtistAliasRepository: CrudRepository<ArtistAlias, UUID>
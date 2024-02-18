package com.ice.trackservice.domain.repository

import com.ice.trackservice.domain.model.MusicTrackMetadata
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MusicTrackMetadataRepository: CrudRepository<MusicTrackMetadata, UUID>

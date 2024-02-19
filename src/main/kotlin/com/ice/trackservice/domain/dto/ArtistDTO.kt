package com.ice.trackservice.domain.dto

import java.util.*

data class ArtistDTO(val id: UUID, val name: String, val aliases: Set<String>)
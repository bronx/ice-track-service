package com.ice.trackservice.api.controller

import com.ice.trackservice.domain.service.ArtistService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/artists")
class ArtistController(private val artistService: ArtistService) {
    // TBD
}

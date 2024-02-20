package com.ice.trackservice.integration.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.ice.trackservice.domain.dto.MusicTrackDTO
import com.ice.trackservice.domain.dto.NewMusicTrackDTO
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.When
import io.restassured.module.kotlin.extensions.Then
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.TestPropertySource
import java.util.UUID.fromString
import java.util.UUID.randomUUID


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = ["classpath:application-it.properties"])
class MusicTrackIT(@LocalServerPort private val serverPort: Int, @Autowired private val mapper: ObjectMapper) {

	@BeforeEach
	fun setup() {
		RestAssured.port = serverPort
	}

	// ADD TRACK TO AN ARTIST

	@Test
	fun `should get a 404 status code (not found) when trying to add a music track to an unknown artist`() {
		Given {
			contentType(ContentType.JSON)
			body(NewMusicTrackDTO(randomUUID(), "A new song", 340))
		} When {
			post("/api/artists/11111111-1111-1111-1111-111111111111/tracks")
		} Then {
			statusCode(404)
			body("message", equalTo("Artist (11111111-1111-1111-1111-111111111111) not found!"))
		}
	}

	@Test
	fun `should get a 400 status code (bad request) when trying to add a music track with an unknown genre id`() {
		Given {
			contentType(ContentType.JSON)
			body(NewMusicTrackDTO(fromString("66666666-6666-6666-6666-666666666666"), "A new song", 340))
		} When {
			post("/api/artists/22222222-2222-2222-2222-222222222222/tracks")
		} Then {
			statusCode(400)
			body("message", equalTo("Genre (66666666-6666-6666-6666-666666666666) not found!"))
		}
	}

	@Test
	fun `should get a 400 status code (bad request) when trying to add a music track with an empty title`() {
		Given {
			contentType(ContentType.JSON)
			body(NewMusicTrackDTO(fromString("11111111-1111-1111-1111-111111111111"), "", 340))
		} When {
			post("/api/artists/22222222-2222-2222-2222-222222222222/tracks")
		} Then {
			statusCode(400)
			body("message", equalTo("Track title cannot be empty nor blank!"))
		}
	}

	@Test
	fun `should get a 400 status code (bad request) when trying to add a music track with an invalid length`() {
		Given {
			contentType(ContentType.JSON)
			body(NewMusicTrackDTO(fromString("11111111-1111-1111-1111-111111111111"), "A new song", 0))
		} When {
			post("/api/artists/22222222-2222-2222-2222-222222222222/tracks")
		} Then {
			statusCode(400)
			body("message", equalTo("Invalid track length! (0)"))
		}
	}

	@Test
	fun `should get a 400 status code (bad request) when trying to add a music track that already exists`() {
		Given {
			contentType(ContentType.JSON)
			body(NewMusicTrackDTO(fromString("11111111-1111-1111-1111-111111111111"), "Country Song", 340))
		} When {
			post("/api/artists/22222222-2222-2222-2222-222222222222/tracks")
		} Then {
			statusCode(400)
			body("message", equalTo("Duplicated track! (Country Song)"))
		}
	}

	@Test
	fun `should successfully add a new music track`() {
		Given {
			contentType(ContentType.JSON)
			body(NewMusicTrackDTO(fromString("11111111-1111-1111-1111-111111111111"), "A new song", 340))
		} When {
			post("/api/artists/22222222-2222-2222-2222-222222222222/tracks")
		} Then {
			statusCode(200)
			// could also check the db!
		}
	}

	// GET TRACKS OF A GIVEN ARTIST

	@Test
	fun `should get a 404 status code (not found) when trying to get music tracks of an unknown artist`() {
		Given {
			contentType(ContentType.JSON)
		} When {
			get("/api/artists/11111111-1111-1111-1111-111111111111/tracks")
		} Then {
			statusCode(404)
			body("message", equalTo("Artist (11111111-1111-1111-1111-111111111111) not found!"))
		}
	}

	@Test
	fun `should successfully get the music tracks of an artist`() {
		Given {
			contentType(ContentType.JSON)
		} When {
			get("/api/artists/33333333-3333-3333-3333-333333333333/tracks")
		} Then {
			statusCode(200)
		} Extract {
			body().asString().let { body ->
				val tracks = mapper.readValue<List<MusicTrackDTO>>(body)
				assertEquals(2, tracks.size)
				assertTrue(tracks.any { it.title == "Malandro É Malandro E Mané É Mané" })
				assertTrue(tracks.any { it.title == "A Semente" })
			}
		}
	}
}
package com.ice.trackservice.integration.test

import com.ice.trackservice.domain.dto.NewAliasDTO
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItems
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.TestPropertySource


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = ["classpath:application-it.properties"])
class ArtistIT(@LocalServerPort private val serverPort: Int) {

	@BeforeEach
	fun setup() {
		RestAssured.port = serverPort
	}

	// ADD NEW ARTIST NAME/ALIAS

	@Test
	fun `should get a 404 status code (not found) when trying to add a new name to an unknown artist`() {
		Given {
			contentType(ContentType.JSON)
			body(NewAliasDTO("Mary"))
		} When {
			post("/api/artists/11111111-1111-1111-1111-111111111111/alias")
		} Then {
			statusCode(404)
			body("message", equalTo("Artist (11111111-1111-1111-1111-111111111111) not found!"))
		}
	}


	@Test
	fun `should get a 400 status code (bad request) when trying to add an empty alias`() {
		Given {
			contentType(ContentType.JSON)
			body(NewAliasDTO(""))
		} When {
			post("/api/artists/44444444-4444-4444-4444-444444444444/alias")
		} Then {
			statusCode(400)
			body("message", equalTo("Alias cannot be empty nor blank!"))
		}
	}

	@Test
	fun `should successfully add a new alias`() {
		Given {
			contentType(ContentType.JSON)
			body(NewAliasDTO("Miley Cyrus"))
		} When {
			post("/api/artists/44444444-4444-4444-4444-444444444444/alias")
		} Then {
			statusCode(200)
			body("name", equalTo("Miley Cyrus")) // Sets new alias as current name!
			body("aliases", hasItems("Miley Cyrus", "Hannah Montana"))
		}
	}


	// GET ARTIST OF THE DAY

	@Test
	fun `should get an empty result when getting the artist of the day but there is no artist yet registered`() {
	}

	@Test
	fun `should consistently get an artist that had already been selected for today`() {
	}

	@Test
	fun `should get an artist that had never been selected before when no one has been selected for today`() {
	}

	@Test
	fun `should properly rotate artist of the day`() {
	}

	@Test
	fun `should get artist whose last time as artist of the day was the farthest in the past when an artist is somehow removed`() {
	}
}
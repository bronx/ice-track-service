package com.ice.trackservice

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with

@TestConfiguration(proxyBeanMethods = false)
class TestTrackServiceApplication

fun main(args: Array<String>) {
	fromApplication<TrackServiceApplication>()
		.with(TestTrackServiceApplication::class)
		.run(*args)
}

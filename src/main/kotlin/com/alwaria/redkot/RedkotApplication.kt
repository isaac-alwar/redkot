package com.alwaria.redkot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class RedkotApplication
	fun main(args: Array<String>) {
		runApplication<RedkotApplication>(*args)
	}


/*
	@Bean
	fun rxtemplate(): ReactiveRedisTemplate<String, Int> {
		return ReactiveRedisTemplate<String, Int>().apply {
		}
	}
*/


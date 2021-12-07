package com.alwaria.redkot

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RedkotApplicationTests {

	@Test
	fun generatesJWTs() {
		for (index in 1..100){
			val jKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)
			println(Encoders.BASE64.encode(jKey.encoded))
		}

	}

	@Test
	fun contextLoads() {
	}

}

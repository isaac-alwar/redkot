package com.alwaria.redkot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import java.time.Duration

//@Configuration
class RedSentinelConf {

//    @Bean
//    @Primary
//    fun lettuceConnectionFactory(): ReactiveRedisConnectionFactory {
////        val clientConf= LettuceClientConfiguration.builder()
////            .useSsl()
////            .and()
////            .commandTimeout(Duration.ofSeconds(2))
////            .shutdownTimeout(Duration.ZERO)
////            .build()
////            .master("localhost")
////            .sentinel("127.0.0.1", 6989 )
////            .sentinel("127.0.0.1", 6989)
//        return LettuceConnectionFactory(RedisStandaloneConfiguration("localhost", 6989))
//
//    }
}
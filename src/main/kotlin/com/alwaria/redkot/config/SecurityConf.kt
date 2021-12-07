package com.alwaria.redkot.config

import com.alwaria.redkot.components.JwtAuthenticationManager
import com.alwaria.redkot.components.JwtServerAuthenticationConverter
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import reactor.core.publisher.Mono

@EnableWebFluxSecurity
class SecurityConf {

    @Bean
    fun pwdEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService(encoder: PasswordEncoder): MapReactiveUserDetailsService {
        val usr = User.builder()
            .username("bat")
            .password(encoder.encode("passwd"))
            .roles("USER")
            .build()

        return MapReactiveUserDetailsService(usr)
    }

    @Bean
    fun springSecurityFilterChain(
        http: ServerHttpSecurity,
        converter: JwtServerAuthenticationConverter,
        authManager: JwtAuthenticationManager):
            SecurityWebFilterChain? {
        val filter = AuthenticationWebFilter(authManager)
        filter.setServerAuthenticationConverter(converter)

        http
            .exceptionHandling()
            .authenticationEntryPoint { exchange, _ ->
                Mono.fromRunnable {
                    exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                    exchange.response.headers.set(HttpHeaders.WWW_AUTHENTICATE, "Bearer")
                }
            }
            .and()
            .authorizeExchange()
            .pathMatchers(HttpMethod.POST, "/login").permitAll()
            .anyExchange().authenticated()
            .and()
            .addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION)
            .httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()

        return http.build()
    }

}
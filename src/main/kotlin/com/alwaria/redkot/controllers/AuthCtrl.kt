package com.alwaria.redkot.controllers

import com.alwaria.redkot.components.JwtSupport
import com.alwaria.redkot.models.Jwt
import com.alwaria.redkot.models.UserLogin
import com.alwaria.redkot.models.UserProfile
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.security.Principal

@RestController
class AuthCtrl(
    private val jwtSupport: JwtSupport,
    private val encoder: PasswordEncoder,
    private val users: ReactiveUserDetailsService)
        {
            @GetMapping("/me")
            suspend fun me(@AuthenticationPrincipal principal: Principal): UserProfile {
                return UserProfile(principal.name)
            }

            @PostMapping("/login")
            suspend fun login(@RequestBody login: UserLogin): Jwt {
                val usr = users.findByUsername(login.username).awaitSingleOrNull()

                usr?.let {
                    if (encoder.matches(login.password, it.password)) {

                        return Jwt(jwtSupport.generate(it.username).value)
                    }
                }

                throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
            }
        }
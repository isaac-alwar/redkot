package com.alwaria.redkot.components

import com.alwaria.redkot.models.UserProfile
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Function


@Repository
class UserProfileService {
private var rxUserProfileOps: ReactiveRedisOperations<String?, UserProfile?>? = null

fun UserProfileService(rxUserProfileOps: ReactiveRedisOperations<String?, UserProfile?>) {
    this.rxUserProfileOps = rxUserProfileOps
}

fun findAll(): Flux<UserProfile?>? {
    return rxUserProfileOps?.opsForHash<String, UserProfile>()?.values("eduspot-user-profiles")
}

fun findById(id: String?): Mono<UserProfile?>? {
    return id?.let { rxUserProfileOps!!.opsForHash<String, UserProfile>().get("eduspot-user-profile", it) }
}

fun addUserProfile(userprofile: UserProfile): Mono<UserProfile> {
    return rxUserProfileOps!!
        .opsForHash<String, UserProfile>()
        .put("eduspot-user-profile", userprofile.username, userprofile)
        .map { userprofile }
}

//    fun updateUserProfile(id: String?, currentUserProfile: UserProfile): Mono<UserProfile> {
//        findById(id)
//        return addUserProfile(currentUserProfile)
//    }

fun delete(id: String?): Mono<Boolean?>? {
    return id?.let { rxUserProfileOps!!.opsForHash<String, UserProfile>().delete(it) }
}

    fun updateUserProfile(id: String, currentUserProfile: UserProfile): Mono<UserProfile> {
        findById(id)
        return addUserProfile(currentUserProfile)
    }
}


/*
class UserRepo(val rxtemplate: ReactiveRedisOperations<UserProfile, String>)
{
    private val log = LoggerFactory.getLogger(this::class.java)

}



    fun findByLogin(login: Flow<UserLogin>): Mono<UserProfile> {
        return
    }
    }
 */
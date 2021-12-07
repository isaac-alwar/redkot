package com.alwaria.redkot.controllers

import com.alwaria.redkot.components.UserProfileService
import com.alwaria.redkot.models.UserProfile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UsersCtrl {

    @Autowired
    private val userRepo: UserProfileService? = null

        @GetMapping("/get/{username}")
        fun getUser(@RequestParam("username") username: String): String {
            val usr = userRepo?.findById(username)
            println("########### GET a Customers with $usr")
            return usr.toString()
        }

        @PostMapping("/post")
        fun newUserProfile(@RequestBody user: UserProfile): String{
            // do post
            userRepo?.addUserProfile(user)
            // log on console
            println("########### POST:$user")

            return "Post Successfully!"
        }

        @PutMapping("/put/{id}")
        fun updateUserProfile(@PathVariable name: String, @RequestBody currentUsrProfile: UserProfile): String{
            // reset customer.Id
//            currentUsrProfile.username = name


            userRepo?.updateUserProfile(name, currentUsrProfile)

            println("########### PUT:$currentUsrProfile")
            return "Put Successfully!"


        }

        @DeleteMapping("/delete/{id}")
        fun deleteMethod(@PathVariable id: String): String {
            val xuser = userRepo?.delete(id)
                if(xuser != null){
                    println("########### DELETE: :$id")
                        }else{
                println("########### Don't exist any customer with id = $id")
            }
            return "Done!"
        }
}



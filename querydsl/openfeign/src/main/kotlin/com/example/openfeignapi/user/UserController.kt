package com.example.openfeignapi.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class UserController(
    private val userRepository: UserRepository,
) {
    @GetMapping("users")
    fun getUsers(): List<UserEntity> {
        return userRepository.findAll()
    }
}

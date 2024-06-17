package com.example.kotestsample.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ping")
class PingController {
    @GetMapping
    fun getPong(): Map<String, String> =
        mapOf(
            "status" to "Pong",
        )
}

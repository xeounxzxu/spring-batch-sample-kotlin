package com.example.basic.web

import com.example.basic.core.extents.PublicAPI
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class HelloDto(val kr: String = "안녕", val en: String = "hello")

@RestController
@RequestMapping("v1")
class HelloController {
    @PublicAPI
    @GetMapping("hello")
    fun getHello(): HelloDto = HelloDto()
}

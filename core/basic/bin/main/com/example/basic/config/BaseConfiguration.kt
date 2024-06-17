package com.example.basic.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BaseConfiguration {
    @Bean("defaultYn")
    fun defaultBoolean() = true
}

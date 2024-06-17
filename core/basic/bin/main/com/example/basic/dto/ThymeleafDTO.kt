package com.example.basic.dto

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "thymeleaf")
data class ThymeleafDTO constructor(
    var mode: String? = null,
    var prefix: String? = null,
    var suffix: String? = null,
    var cache: Boolean? = null,
    var checkTemplateLocation: Boolean? = null,
    var characterEncoding: String? = null,
)

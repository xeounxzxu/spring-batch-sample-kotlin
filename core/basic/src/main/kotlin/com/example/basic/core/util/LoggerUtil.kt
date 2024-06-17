package com.example.basic.core.util

import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
object LoggerUtil {
    val logger = KotlinLogging.logger {}
}

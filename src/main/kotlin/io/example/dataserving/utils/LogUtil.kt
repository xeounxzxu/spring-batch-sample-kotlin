package io.example.dataserving.utils

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class LogUtil {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun getLogger() = this.log
}
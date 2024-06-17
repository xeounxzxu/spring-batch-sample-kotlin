package com.example.dynamicbean

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DynamicBeanApplication

fun main(args: Array<String>) {
    runApplication<DynamicBeanApplication>(*args)
}

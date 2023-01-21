package com.example.batchapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
open class BootApplication

fun main(args: Array<String>) {
    runApplication<BootApplication>(*args)
}
